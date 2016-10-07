package de.seven.fate.event.service;

import de.seven.fate.event.MessageEventData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Slf4j
@Component
public class MessageEventService {

    @Inject
    private ApplicationEventPublisher eventPublisher;

    @PostConstruct
    private void init() { //NOSONAR
        log.debug("init " + MessageEventService.class.getCanonicalName());
    }

    public void firePersistEvent(String ldapId) {

        eventPublisher.publishEvent(new MessageEventData(ldapId, null));
    }

    public void fireUpdateEvent(String ldapId) {

        eventPublisher.publishEvent(new MessageEventData(ldapId, null));
    }
}