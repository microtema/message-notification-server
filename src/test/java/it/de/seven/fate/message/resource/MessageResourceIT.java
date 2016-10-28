package it.de.seven.fate.message.resource;

import de.seven.fate.Application;
import de.seven.fate.annotation.Model;
import de.seven.fate.enums.ModelType;
import de.seven.fate.message.bo.MessageBO;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.person.dao.PersonDAO;
import de.seven.fate.person.domain.Person;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageResourceIT {

    public static final ParameterizedTypeReference<List<MessageBO>> LIST_MESSAGE_BO_TYPE = new ParameterizedTypeReference<List<MessageBO>>() {
    };

    @Inject
    TestRestTemplate restTemplate;

    @Model(type = ModelType.FIX)
    Person person;

    @Inject
    PersonDAO dao;

    @Before
    public void setUp() {
        dao.save(person);
    }

    @After
    public void tearDown() {
        dao.deleteAll();
    }

    @Test
    public void getMassages() {
        ResponseEntity<List> entity = restTemplate.getForEntity("/rest/message", List.class);

        assertEquals(entity.getStatusCode(), HttpStatus.OK);

        assertEquals(person.getMessages().size(), entity.getBody().size());
    }

    @Test
    public void getUnreadMessages() {
        String url = "/rest/message/type/{type}";

        ResponseEntity<List<MessageBO>> entity = restTemplate.exchange(url, HttpMethod.GET, null, LIST_MESSAGE_BO_TYPE, singletonMap("type", MessageType.UNREAD));

        assertEquals(entity.getStatusCode(), HttpStatus.OK);

        assertEquals(person.getMessages().stream().filter(message -> message.getMessageType() == MessageType.UNREAD).count(), entity.getBody().size());

        entity.getBody().forEach(messageBO -> {
            assertEquals(messageBO.getType(), MessageType.UNREAD.name());
        });
    }

    @Test
    public void getReadMessages() {
        String url = "/rest/message/type/{type}";

        ResponseEntity<List<MessageBO>> entity = restTemplate.exchange(url, HttpMethod.GET, null, LIST_MESSAGE_BO_TYPE, singletonMap("type", MessageType.READ));

        assertEquals(entity.getStatusCode(), HttpStatus.OK);

        assertEquals(person.getMessages().stream().filter(message -> message.getMessageType() == MessageType.READ).count(), entity.getBody().size());
    }

    @Test
    public void markMessage() {

        List<Long> ids = person.getMessages().stream().map(message -> message.getId()).collect(Collectors.toList());

        String url = "/rest/message/{messageIds}";

        ResponseEntity<MessageType> entity = restTemplate.postForEntity(url, null, MessageType.class, singletonMap("messageIds", StringUtils.join(ids, ",")));

        assertEquals(entity.getStatusCode(), HttpStatus.OK);

        assertEquals(MessageType.READ, entity.getBody());
    }

    @Test
    public void deleteMessage() {

        Long id = person.getMessages().stream().findAny().get().getId();

        String url = "/rest/message/{id}";

        restTemplate.delete(url, singletonMap("id", id));
    }

    @Test
    public void deleteAllMessage() {

        Long id = person.getMessages().stream().findAny().get().getId();

        String url = "/rest/message/all";

        restTemplate.delete(url);
    }

}