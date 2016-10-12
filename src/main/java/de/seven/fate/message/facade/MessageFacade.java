package de.seven.fate.message.facade;

import de.seven.fate.event.MessageEventData;
import de.seven.fate.message.Constants;
import de.seven.fate.message.bo.MessageBO;
import de.seven.fate.message.converter.Message2MessageBOConverter;
import de.seven.fate.message.converter.MessageBO2MessageConverter;
import de.seven.fate.message.domain.Message;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.message.service.MessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

@Log4j2
@Component
public class MessageFacade {

    @Inject
    private MessageService service;

    @Inject
    private Message2MessageBOConverter message2MessageBOConverter;

    @Inject
    private MessageBO2MessageConverter messageBO2MessageConverter;

    /**
     * first lookup in user cache than in DB
     *
     * @param personLdapId
     * @return messages for given ldapId
     */
    @Cacheable(Constants.MESSAGE_CACHE)
    public List<MessageBO> findMessagesByPerson(String personLdapId) {
        notNull(personLdapId);

        List<Message> messages = service.findMessagesByPerson(personLdapId);

        return message2MessageBOConverter.convertList(messages);
    }

    @CacheEvict(cacheNames = Constants.MESSAGE_CACHE, allEntries = true)
    public MessageBO updateMassage(String personLdapId, MessageBO messageBO) {
        notNull(messageBO);

        log.debug("update message from user: " + personLdapId);

        Message message = messageBO2MessageConverter.convert(messageBO);

        Message updateMessage = service.updateMessage(message);

        return message2MessageBOConverter.convert(updateMessage);
    }

    @CacheEvict(cacheNames = Constants.MESSAGE_CACHE, allEntries = true)
    public Boolean deleteMassage(String personLdapId, List<Long> messageIds) { //NOSONAR

        log.debug("delete " + messageIds.size() + " message(s) from user: " + personLdapId);

        for (Long messageId : messageIds) {
            service.removeMessage(messageId);
        }

        return Boolean.TRUE;
    }

    @CacheEvict(cacheNames = Constants.MESSAGE_CACHE, allEntries = true)
    public Boolean deleteMassage(String personLdapId) {

        service.removeAllMessage(personLdapId);

        return Boolean.TRUE;
    }


    public List<MessageBO> findMessagesByPersonAndType(String personLdapId, MessageType messageType) {

        List<Message> messages = service.findMessagesByPersonAndType(personLdapId, messageType);

        return message2MessageBOConverter.convertList(messages);
    }

    @CacheEvict(cacheNames = Constants.MESSAGE_CACHE, allEntries = true)
    public MessageType markMessageAsRead(String personLdapId, List<Long> messageIds) {

        log.debug("mark " + messageIds + " message(s) as read fro user: " + personLdapId);

        service.markMessage(messageIds, MessageType.READ);

        return MessageType.READ;
    }

    @EventListener
    protected void onMessageChangeEvent(MessageEventData eventData) {

        String ldapId = eventData.getLdapId();

        evictCache(ldapId);
    }

    @CacheEvict(cacheNames = Constants.MESSAGE_CACHE, allEntries = true)
    public void evictCache(String ldapId) {
        log.debug("evict user cache for: " + ldapId);
    }
}
