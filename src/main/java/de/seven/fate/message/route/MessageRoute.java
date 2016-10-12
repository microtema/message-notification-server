package de.seven.fate.message.route;

import de.seven.fate.message.dto.MessagesDTO;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.IdempotentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.bind.JAXBException;

import static javax.xml.bind.JAXBContext.newInstance;
import static org.apache.camel.processor.idempotent.MemoryIdempotentRepository.memoryIdempotentRepository;

@Component
public class MessageRoute extends RouteBuilder {

    /**
     * There are many Idempotent-Consumer-Repositories available, we Just peek the Memory one.
     *
     * @see http://camel.apache.org/idempotent-consumer.html
     */
    private static final IdempotentRepository<String> IDEMPOTENT_REPOSITORY = memoryIdempotentRepository(200);


    @Value("${message.route.from}")
    private String fromUri;

    @Value("${message.route.to}")
    private String toUri;

    @Value("${message.route.seda.endpoint}")
    private String sedaEndpoint;

    @Value("${message.schema.location}")
    private String schemaLocation;

    @Inject
    private XmlMessageProcessor messageProcessor;

    private JaxbDataFormat messagesData;

    @PostConstruct
    private void init() throws JAXBException { //NOSONAR

        messagesData = new JaxbDataFormat();

        messagesData.setContext(newInstance(MessagesDTO.class));

        messagesData.setSchema(schemaLocation);
    }


    /**
     * create two Endpoints with a small EIP to prevent duplicates on same messages with same uuid
     *
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {

        from(fromUri)
                .routeId("from_uri_to_seda_message_route")
                .unmarshal(messagesData)
                .process(messageProcessor)
                .to(sedaEndpoint);

        from(sedaEndpoint)
                .routeId("from_seda_to_uri_message_route")
                .idempotentConsumer(header("UUID"), IDEMPOTENT_REPOSITORY)
                .to(toUri);
    }
}
