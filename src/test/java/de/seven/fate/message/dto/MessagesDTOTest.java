package de.seven.fate.message.dto;

import org.junit.Test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mario on 08.10.2016.
 */
public class MessagesDTOTest {


    MessagesDTO sut = new MessagesDTO();

    @Test
    public void testImplementation() {
        assertTrue(Serializable.class.isAssignableFrom(sut.getClass()));
    }

    @Test
    public void shouldBeAnnotatedXmlRootElement() {
        assertTrue(sut.getClass().isAnnotationPresent(XmlRootElement.class));
    }

    @Test
    public void fieldMessagesShouldBeAnnotatedAsXmlRootElement() throws Exception {

        assertTrue(sut.getClass().getDeclaredField("messages").isAnnotationPresent(XmlElement.class));
    }

    @Test
    public void fieldMessagesShouldBeRequired() throws Exception {

        assertTrue(sut.getClass().getDeclaredField("messages").getAnnotation(XmlElement.class).required());
    }

}