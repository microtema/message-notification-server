package it.de.seven.fate.message.dao;

import de.seven.fate.Application;
import de.seven.fate.annotation.Model;
import de.seven.fate.annotation.Models;
import de.seven.fate.enums.ModelsType;
import de.seven.fate.message.dao.MessageDAO;
import de.seven.fate.message.domain.Message;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.person.dao.PersonDAO;
import de.seven.fate.person.domain.BaseEntity;
import de.seven.fate.person.domain.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@Transactional
public class MessageDAOTest {

    @Inject
    MessageDAO sut;

    @Inject
    PersonDAO personDAO;

    @Model
    Message model;

    @Model
    Person person;

    @Models(type = ModelsType.LIST, size = -1)
    List<Message> models;

    @Before
    public void setUp() {

        sut.deleteAll();
        model = person.getMessages().stream().findAny().get();
        personDAO.save(person);
    }

    @Test
    public void findOne() {
        assertEquals(model, sut.findOne(model.getId()));
    }

    @Test
    public void findByPerson() throws Exception {
        assertEquals(model, sut.findFirstByPerson(model.getPerson()));
    }

    @Test
    public void markMessage() throws Exception {

        //given
        List<Long> messageIds = person.getMessages().stream().map(BaseEntity::getId).collect(Collectors.toList());
        MessageType messageType = MessageType.READ;

        //when
        int markedMessageCount = sut.markMessage(messageIds, messageType);

        //then
        assertEquals(messageIds.size(), markedMessageCount);
    }
}