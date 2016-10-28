package de.seven.fate.message.route;

import de.seven.fate.message.dto.MessagesDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class XmlMessageProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        MessagesDTO messagesDTO = exchange.getIn().getBody(MessagesDTO.class);

        String uuid = messagesDTO.getUuid();

        log.debug("Set UUID[" + uuid + "] for From-Route-ID: " + exchange.getFromRouteId());

        exchange.getOut().setHeader("UUID", uuid);

        exchange.getOut().setBody(messagesDTO);
    }
}