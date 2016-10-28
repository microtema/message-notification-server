package de.seven.fate.message.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageCountProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Map<Object, Object> body = exchange.getIn().getBody(Map.class);
        exchange.getIn().setBody("There are " + body.get("count") + " message(s) in Database.");
    }
}