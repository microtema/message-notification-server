package de.seven.fate.message.listener;

import de.seven.fate.event.service.MessageEventService;
import de.seven.fate.message.domain.Message;
import de.seven.fate.util.AutowireHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.PostConstruct;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class MessageListener {

    @Autowired
    private MessageEventService eventService;

    @PostConstruct
    public void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @PostUpdate
    void postUpdate(Object object) {

       // SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        AutowireHelper.autowire(this, this.eventService);

        if (object instanceof Message) {
            eventService.fireUpdateEvent(((Message) object).getPerson().getLdapId());
        }
    }

    @PostPersist
    void postPersist(Object object) {

       // SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        AutowireHelper.autowire(this, this.eventService);

        if (object instanceof Message) {
            eventService.firePersistEvent(((Message) object).getPerson().getLdapId());
        }
    }

}