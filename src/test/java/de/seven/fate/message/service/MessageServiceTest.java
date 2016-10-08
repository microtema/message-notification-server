package de.seven.fate.message.service;

import de.seven.fate.message.dao.MessageDAO;
import de.seven.fate.message.domain.Message;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.person.dao.PersonDAO;
import de.seven.fate.person.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @InjectMocks
    MessageService sut;
    @Mock
    Message message;
    @Mock
    Person person;
    @Mock
    private MessageDAO dao;
    @Mock
    private PersonDAO personDAO;

    @Test
    public void getMessage() throws Exception {

        when(message.getId()).thenReturn(0l);

        sut.getMessage(message);

        verify(dao).findOne(anyLong());
    }

    @Test
    public void findMessagesByPerson() throws Exception {

        sut.findMessagesByPerson(anyString());

        verify(dao).findByPersonLdapId(anyString());
    }

    @Test
    public void findMessagesByPersonAndType() throws Exception {

        sut.findMessagesByPersonAndType(anyString(), any(MessageType.class));

        verify(dao).findByPersonLdapIdAndMessageType(anyString(), any(MessageType.class));
    }

    @Test
    public void removeMessage() throws Exception {

        sut.removeMessage(message);

        verify(message).setPerson(null);
        verify(dao).delete(message);
    }

    @Test
    public void removeMessage1() throws Exception {

        when(dao.findOne(anyLong())).thenReturn(message);

        sut.removeMessage(anyLong());

        verify(message).setPerson(null);
        verify(dao).delete(message);
    }

    @Test
    public void saveMessage() throws Exception {

        when(message.getPerson()).thenReturn(person);

        sut.saveMessage(message);

        verify(dao).save(Arrays.asList(message));
    }

    @Test
    public void saveMessageList() throws Exception {

        when(message.getPerson()).thenReturn(person);

        sut.saveMessage(Arrays.asList(message));

        verify(dao).save(Arrays.asList(message));
    }

    @Test
    public void updateMessage() throws Exception {

        sut.updateMessage(message);

        verify(dao).save(message);
    }

    @Test
    public void saveMessageListWIthPerson() throws Exception {

        sut.saveMessage(Arrays.asList(message), person);

        verify(dao).save(Arrays.asList(message));
    }

    @Test
    public void removeAllMessage() throws Exception {

        when(personDAO.findByLdapId(anyString())).thenReturn(person);

        sut.removeAllMessage(anyString());

        verify(dao).delete(anyListOf(Message.class));
    }

    @Test
    public void markMessage() throws Exception {

        sut.markMessage(Arrays.asList(0l), MessageType.READ);

        verify(dao).markMessage(anyListOf(Long.class), any(MessageType.class));
    }

}