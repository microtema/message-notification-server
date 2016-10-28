package de.seven.fate.person.dto;

import org.junit.Test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

import static org.junit.Assert.assertTrue;


public class PersonDTOTest {


    PersonDTO sut = new PersonDTO();

    @Test
    public void testImplementation() {
        assertTrue(Serializable.class.isAssignableFrom(sut.getClass()));
    }

    @Test
    public void shouldBeAnnotatedXmlRootElement() {
        assertTrue(sut.getClass().isAnnotationPresent(XmlRootElement.class));
    }

    @Test
    public void fieldPersonLdapIdShouldBeAnnotatedAsXmlRootElement() throws Exception {

        assertTrue(sut.getClass().getDeclaredField("ldapId").isAnnotationPresent(XmlElement.class));
    }

    @Test
    public void fieldPersonLdapIdShouldBeRequired() throws Exception {

        assertTrue(sut.getClass().getDeclaredField("ldapId").getAnnotation(XmlElement.class).required());
    }
}