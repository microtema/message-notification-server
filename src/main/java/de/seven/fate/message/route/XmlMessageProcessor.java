package de.seven.fate.message.route;

import de.seven.fate.message.dto.MessagesDTO;
import de.seven.fate.message.service.XmlMessageService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class XmlMessageProcessor implements Processor {

    @Inject
    private XmlMessageService messageService;

    @Override
    public void process(Exchange exchange) throws Exception {

        MessagesDTO messagesDTO = exchange.getIn().getBody(MessagesDTO.class);

        messageService.process(messagesDTO.getMessages());
    }
}