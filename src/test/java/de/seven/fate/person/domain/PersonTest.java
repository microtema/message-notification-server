package de.seven.fate.person.domain;

import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonTest {

    Person sut = new Person();

    @Test
    public void testImplementation() {
        assertTrue(Serializable.class.isAssignableFrom(sut.getClass()));
    }

    @Test
    public void shouldBeAnnotatedAsEntity() {
        assertTrue(sut.getClass().isAnnotationPresent(Entity.class));
    }

    @Test
    public void shouldOwnPrimaryKey() throws Exception {
        assertTrue(sut.getClass().getDeclaredField("id").isAnnotationPresent(Id.class));
    }

    @Test
    public void shouldOwnPrimaryKeyAsGeneratedValue() throws Exception {
        GeneratedValue generatedValue = sut.getClass().getDeclaredField("id").getAnnotation(GeneratedValue.class);
        assertNotNull(generatedValue);
        assertEquals(GenerationType.AUTO, generatedValue.strategy());
    }
}