package de.seven.fate.message.domain;

import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

import static org.junit.Assert.*;

public class MessageTest {

    Message sut = new Message();

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