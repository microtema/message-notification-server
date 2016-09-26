package de.seven.fate.message.route;

import de.seven.fate.message.dto.MessagesDTO;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Component
public class MessageRoute extends RouteBuilder {

    private final JaxbDataFormat messagesData = new JaxbDataFormat();


    @Value("${route.message.from}")
    private String uri;

    @Inject
    private XmlMessageProcessor messageProcessor;

    @Inject
    private CamelContext camelContext;

    @PostConstruct
    private void init() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(MessagesDTO.class);

        messagesData.setContext(jaxbContext);
    }

    @Override
    public void configure() throws Exception {
       from(uri).unmarshal(messagesData).process(messageProcessor);
    }
}
