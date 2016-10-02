package de.seven.fate.message.facade;

import de.seven.fate.event.MessageEventData;
import de.seven.fate.message.Constants;
import de.seven.fate.message.bo.MessageBO;
import de.seven.fate.message.converter.Message2MessageBOModelConverter;
import de.seven.fate.message.converter.MessageBO2MessageModelConverter;
import de.seven.fate.message.domain.Message;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.message.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Slf4j
@Component
public class MessageFacade {

    @Inject
    private MessageService service;

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
    @Cacheable(Constants.MESSAGE_CACHE)
    public List<MessageBO> findMessagesByPerson(String ldapId) {
        Validate.notNull(ldapId);

        List<Message> messages = service.findMessagesByPerson(ldapId);

        return message2MessageBOConverter.convertList(messages);
    }

    @CacheEvict(cacheNames = Constants.MESSAGE_CACHE, allEntries = true)
    public MessageBO updateMassage(String ldapId, MessageBO messageBO) {
        Validate.notNull(messageBO);

        Message message = messageBO2MessageConverter.convert(messageBO);

        Message updateMessage = service.updateMessage(message);

        return message2MessageBOConverter.convert(updateMessage);
    }

    @CacheEvict(cacheNames = Constants.MESSAGE_CACHE, allEntries = true)
    public Boolean deleteMassage(String ldapId, List<Long> messageIds) {

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


    public List<MessageBO> findMessagesByPersonAndType(String userName, MessageType messageType) {

        List<Message> messages = service.findMessagesByPersonAndType(userName, messageType);

        return message2MessageBOConverter.convertList(messages);
    }

    @CacheEvict(cacheNames = Constants.MESSAGE_CACHE, allEntries = true)
    public Boolean markMessageAsRead(String personLdapId, List<Long> messageIds) {

        service.markMessage(messageIds, MessageType.READ);

        return Boolean.TRUE;
    }

    @EventListener
    protected void onMessageChangeEvent(MessageEventData eventData) {

        String ldapId = eventData.getLdapId();

        evictCache(ldapId);
    }

    @CacheEvict(cacheNames = Constants.MESSAGE_CACHE, allEntries = true)
    public void evictCache(String ldapId) {

    }
}
