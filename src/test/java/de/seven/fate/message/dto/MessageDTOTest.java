package de.seven.fate.message.dto;

import org.junit.Test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mario on 08.10.2016.
 */
public class MessageDTOTest {


    MessageDTO sut = new MessageDTO();

    @Test
    public void testImplementation() {
        assertTrue(Serializable.class.isAssignableFrom(sut.getClass()));
    }

    @Test
    public void shouldBeAnnotatedXmlRootElement() {
        assertTrue(sut.getClass().isAnnotationPresent(XmlRootElement.class));
    }

    @Test
    public void fieldPersonShouldBeAnnotatedAsXmlRootElement() throws Exception {

        assertTrue(sut.getClass().getDeclaredField("person").isAnnotationPresent(XmlElement.class));
    }

    @Test
    public void fieldPersonShouldBeRequired() throws Exception {

        assertTrue(sut.getClass().getDeclaredField("person").getAnnotation(XmlElement.class).required());
    }

    @Test
    public void fieldTitleShouldBeAnnotatedAsXmlRootElement() throws Exception {

        assertTrue(sut.getClass().getDeclaredField("title").isAnnotationPresent(XmlElement.class));
    }

    @Test
    public void fieldTitleShouldBeRequired() throws Exception {

        assertTrue(sut.getClass().getDeclaredField("title").getAnnotation(XmlElement.class).required());
    }

    @Test
    public void fieldImageShouldBeAnnotatedAsXmlRootElement() throws Exception {

        assertTrue(sut.getClass().getDeclaredField("image").isAnnotationPresent(XmlElement.class));
    }

    @Test
    public void fieldImageShouldBeRequired() throws Exception {

        assertTrue(sut.getClass().getDeclaredField("image").getAnnotation(XmlElement.class).required());
    }

    @Test
    public void fieldPubDateShouldBeAnnotatedAsXmlRootElement() throws Exception {

        assertTrue(sut.getClass().getDeclaredField("pubDate").isAnnotationPresent(XmlElement.class));
    }

    @Test
    public void fieldPubDateShouldBeRequired() throws Exception {

        assertFalse(sut.getClass().getDeclaredField("pubDate").getAnnotation(XmlElement.class).required());
    }
}