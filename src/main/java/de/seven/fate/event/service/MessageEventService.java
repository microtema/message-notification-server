package de.seven.fate.event.service;

import de.seven.fate.event.MessageEventData;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Component
public class MessageEventService {

    @Inject
    private ApplicationEventPublisher eventPublisher;


    public void firePersistEvent(String ldapId) {

        eventPublisher.publishEvent(new MessageEventData(ldapId, null));
    }

    public void fireUpdateEvent(String ldapId) {

        eventPublisher.publishEvent(new MessageEventData(ldapId, null));
    }
}