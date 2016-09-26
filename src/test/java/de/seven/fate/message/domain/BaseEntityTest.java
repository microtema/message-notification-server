package de.seven.fate.message.domain;

import de.seven.fate.person.domain.BaseEntity;
import org.junit.Test;

import javax.persistence.*;
import java.io.Serializable;

import static org.junit.Assert.*;

public class BaseEntityTest {

    BaseEntity sut = new BaseEntity();

    @Test
    public void testImplementation() {
        assertTrue(Serializable.class.isAssignableFrom(sut.getClass()));
    }

    @Test
    public void shouldBeAnnotatedAsMappedSuperclass() {
        assertTrue(sut.getClass().isAnnotationPresent(MappedSuperclass.class));
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