package de.seven.fate.message.route;

import de.seven.fate.message.dto.MessagesDTO;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.bind.JAXBException;

import static javax.xml.bind.JAXBContext.newInstance;

@Component
public class MessageRoute extends RouteBuilder {

    @Value("${route.message.from}")
    private String uri;

    private String schemaLocation;

    @Inject
    private XmlMessageProcessor messageProcessor;

    private JaxbDataFormat messagesData;

    @PostConstruct
    private void init() throws JAXBException {

        messagesData = new JaxbDataFormat();

        messagesData.setContext(newInstance(MessagesDTO.class));

        messagesData.setSchema("classpath:message-schema.xsd");
    }

    @Override
    public void configure() throws Exception {

        from(uri).unmarshal(messagesData).process(messageProcessor);
    }
}
