package de.seven.fate.message.domain;

import de.seven.fate.person.domain.BaseEntity;
import org.junit.Test;

import javax.persistence.Entity;

import static org.junit.Assert.assertTrue;

public class MessageTest {

    Message sut = new Message();

    @Test
    public void testImplementation() {
        assertTrue(BaseEntity.class.isAssignableFrom(sut.getClass()));
    }

    @Test
    public void shouldBeAnnotatedAsEntity() {
        assertTrue(sut.getClass().isAnnotationPresent(Entity.class));
    }

}