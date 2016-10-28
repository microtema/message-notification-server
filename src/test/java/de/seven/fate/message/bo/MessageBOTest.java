package de.seven.fate.message.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mario on 08.10.2016.
 */
public class MessageBOTest {

    MessageBO sut = new MessageBO();

    @Test
    public void testImplementation() {
        assertTrue(Serializable.class.isAssignableFrom(sut.getClass()));
    }

    @Test
    public void shouldBeAnnotatedAsJsonIgnoreProperties() {
        assertTrue(sut.getClass().isAnnotationPresent(JsonIgnoreProperties.class));
    }
}