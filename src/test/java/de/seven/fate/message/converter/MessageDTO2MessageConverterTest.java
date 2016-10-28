package de.seven.fate.message.converter;

import de.seven.fate.builder.MessageDTOBuilder;
import de.seven.fate.message.domain.Message;
import de.seven.fate.message.dto.MessageDTO;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.person.converter.PersonDTO2PersonConverter;
import org.junit.Test;

import static org.junit.Assert.*;


public class MessageDTO2MessageConverterTest {

    MessageDTO2MessageConverter sut = new MessageDTO2MessageConverter(new PersonDTO2PersonConverter());

    MessageDTOBuilder builder = new MessageDTOBuilder();

    MessageDTO dto = builder.max();

    @Test
    public void convertShouldReturnNull() {

        assertNull(sut.convert(null));
    }

    @Test(expected = NullPointerException.class)
    public void updateShouldThrowException() throws Exception {

        sut.update(null, dto);
    }

    @Test
    public void update() {

        Message model = sut.convert(dto);

        assertNotNull(model);

        assertEquals(dto.getDescription(), model.getDescription());
        assertEquals(dto.getImage(), model.getImage());
        assertEquals(dto.getPerson(), model.getPerson());
        assertEquals(dto.getPubDate(), model.getPubDate());
        assertEquals(dto.getTitle(), model.getTitle());
        assertEquals(MessageType.UNREAD, model.getMessageType());
    }

}