package de.seven.fate.message.converter;

import de.seven.fate.builder.MessageBuilder;
import de.seven.fate.message.bo.MessageBO;
import de.seven.fate.message.domain.Message;
import org.junit.Test;

import static org.junit.Assert.*;

public class Message2MessageBOModelConverterTest {

    Message2MessageBOModelConverter sut = new Message2MessageBOModelConverter();

    MessageBO2MessageModelConverter messageBO2MessageModelConverter = new MessageBO2MessageModelConverter();

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

        assertEquals(model, messageBO2MessageModelConverter.convert(bo));
    }

}