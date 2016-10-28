package de.seven.fate.message.converter;

import de.seven.fate.builder.MessageBuilder;
import de.seven.fate.message.bo.MessageBO;
import de.seven.fate.message.domain.Message;
import org.junit.Test;

import static org.junit.Assert.*;

public class Message2MessageBOConverterTest {

    Message2MessageBOConverter sut = new Message2MessageBOConverter();

    MessageBO2MessageConverter messageBO2MessageConverter = new MessageBO2MessageConverter();

    MessageBuilder builder = new MessageBuilder();

    Message model = builder.max();

    @Test
    public void convertShouldReturnNull() {

        assertNull(sut.convert(null));
    }

    @Test(expected = NullPointerException.class)
    public void updateShouldThrowException() throws Exception {

        sut.update(null, model);
    }

    @Test
    public void convert() {

        MessageBO bo = sut.convert(model);
        assertNotNull(bo);

        assertEquals(model, messageBO2MessageConverter.convert(bo));
    }

}