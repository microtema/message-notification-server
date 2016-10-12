package it.de.seven.fate.message.route;

import de.seven.fate.Application;
import de.seven.fate.message.service.MessageService;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MessageRouteIT {

    @EndpointInject(uri = "{{message.route.from}}")
    ProducerTemplate messageSource;

    @Inject
    MessageService service;

    String XML;

    @Before
    public void setUp() throws Exception {

        XML = IOUtils.toString(this.getClass().getResourceAsStream("/messages.xml"));
    }

    @Test(timeout = 3000)
    public void receivedMessagesShouldBePersisted() throws Exception {

        sendMessages();
        testMessages();
    }

    @Async
    private void sendMessages() {

        messageSource.sendBody(XML);
    }

    @Async
    private void testMessages() throws Exception { //NOSONAR

        while (service.findMessagesByPerson("mtema").size() != 12) {
            Thread.sleep(100);
        }

        assertTrue(true);
    }

}