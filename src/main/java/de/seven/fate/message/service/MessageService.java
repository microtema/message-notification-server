package de.seven.fate.message.service;

import de.seven.fate.message.dao.MessageDAO;
import de.seven.fate.message.domain.Message;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.person.dao.PersonDAO;
import de.seven.fate.person.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.lang3.Validate.notNull;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Inject
    private MessageDAO dao;

    @Inject
    private PersonDAO personDAO;


    public Message getMessage(Message message) {

        return dao.findOne(message.getId());
    }

    public List<Message> findMessagesByPerson(String ldapId) {

        return dao.findByPersonLdapId(ldapId);
    }

    public List<Message> findMessagesByPersonAndType(String userName, MessageType messageType) {

        return dao.findByPersonLdapIdAndMessageType(userName, messageType);
    }

    public void removeMessage(Message message) {

        dao.delete(message);
    }

    public void removeMessage(Long messageId) {

        dao.delete(messageId);
    }

    public void saveMessage(Message message) {

        saveMessage(Arrays.asList(message), message.getPerson());
    }

    public void saveMessage(List<Message> messages) {

        messages.forEach(this::saveMessage);
    }

    public Message updateMessage(Message message) {
        return dao.save(message);
    }

    /**
     * save message only if person exist in DB
     */
    public void saveMessage(List<Message> messages, Person person) {
        notNull(messages);
        notNull(person);

        Person attachedPerson = personDAO.findOne(person.getId());

        if (attachedPerson == null) {

            logger.warn("unable to find person by: " + person.getLdapId() + " message will be ignored");
            return;
        }

        for (Message message : messages) {
            message.setPerson(attachedPerson);
        }

        dao.save(messages);
    }

    public void removeAllMessage(String personLdapId) {
        notNull(personLdapId);

        Person person = personDAO.findByLdapId(personLdapId);

        dao.delete(person.getMessages());
    }


    public void markMessage(List<Long> messageIds, MessageType messageType) {
        notNull(messageType);

        if (isEmpty(messageIds)) {
            return;
        }

        int executeUpdate = dao.markMessage(messageIds, messageType);
        // int executeUpdate = dao.createNamedQuery(Message.UPDATE_TYPE, "ids", messageIds, "messageType", messageType).executeUpdate();

        logger.info("update " + executeUpdate + " messages to type: " + messageType);
    }
}
