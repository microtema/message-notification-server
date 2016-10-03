package de.seven.fate.message.converter;

import de.seven.fate.builder.MessageBOBuilder;
import de.seven.fate.message.bo.MessageBO;
import de.seven.fate.message.domain.Message;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageBO2MessageConverterTest {

    MessageBO2MessageConverter sut = new MessageBO2MessageConverter();

    Message2MessageBOConverter message2MessageBOConverter = new Message2MessageBOConverter();

    MessageBOBuilder builder = new MessageBOBuilder();

    MessageBO bo = builder.max();

    @Test
    public void convertShouldReturnNull() {

        assertNull(sut.convert(null));
    }

    @Test(expected = NullPointerException.class)
    public void updateShouldThrowException() throws Exception {

        sut.update(null, bo);
    }

    @Test
    public void update(){

        Message model = sut.convert(bo);

        assertNotNull(model);

        assertEquals(bo, message2MessageBOConverter.convert(model));
    }

}