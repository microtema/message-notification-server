package de.seven.fate.message.facade;

import de.seven.fate.cache.UserCacheService;
import de.seven.fate.message.bo.MessageBO;
import de.seven.fate.message.converter.Message2MessageBOModelConverter;
import de.seven.fate.message.converter.MessageBO2MessageModelConverter;
import de.seven.fate.message.domain.Message;
import de.seven.fate.message.enums.AttributeName;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.message.service.MessageService;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class MessageFacade {

    @Inject
    private MessageService service;

    @Inject
    private UserCacheService userCacheService;

    @Inject
    private Message2MessageBOModelConverter message2MessageBOConverter;

    @Inject
    private MessageBO2MessageModelConverter messageBO2MessageConverter;

    /**
     * first lookup in user cache than in DB
     *
     * @param ldapId
     * @return messages for given ldapId
     */
    public List<MessageBO> findMessagesByPerson(String ldapId) {
        Validate.notNull(ldapId);

        List<MessageBO> messageBOList = userCacheService.getAttribute(ldapId, AttributeName.MESSAGES);

        if (messageBOList == null) {
            List<Message> messages = service.findMessagesByPerson(ldapId);

            messageBOList = message2MessageBOConverter.convertList(messages);
            userCacheService.setAttribute(ldapId, AttributeName.MESSAGES, messageBOList);
        }

        return messageBOList;
    }

    public MessageBO updateMassage(String ldapId, MessageBO messageBO) {
        Validate.notNull(messageBO);

        userCacheService.removeAttributes(ldapId);

        Message message = messageBO2MessageConverter.convert(messageBO);

        Message updateMessage = service.updateMessage(message);

        return message2MessageBOConverter.convert(updateMessage);
    }

    public Boolean deleteMassage(String ldapId, List<Long> messageIds) {

        for (Long messageId : messageIds) {
            service.removeMessage(messageId);
        }

        userCacheService.removeAttributes(ldapId);

        return Boolean.TRUE;
    }

    public Boolean deleteMassage(String personLdapId) {

        service.removeAllMessage(personLdapId);

        userCacheService.removeAttributes(personLdapId);

        return Boolean.TRUE;
    }

    public List<MessageBO> findMessagesByPersonAndType(String userName, MessageType messageType) {

        List<Message> messages = service.findMessagesByPersonAndType(userName, messageType);

        return message2MessageBOConverter.convertList(messages);
    }

    public Boolean markMessageAsRead(String personLdapId, List<Long> messageIds) {

        service.markMessage(messageIds, MessageType.READ);

        userCacheService.removeAttributes(personLdapId);

        return Boolean.TRUE;
    }
}
