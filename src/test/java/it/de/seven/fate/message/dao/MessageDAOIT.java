package it.de.seven.fate.message.dao;

import de.seven.fate.Application;
import de.seven.fate.annotation.Model;
import de.seven.fate.annotation.Models;
import de.seven.fate.enums.ModelType;
import de.seven.fate.enums.ModelsType;
import de.seven.fate.message.dao.MessageDAO;
import de.seven.fate.message.domain.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class MessageDAOIT {

    @Inject
    MessageDAO sut;

    @Model(type = ModelType.MIN)
    Message model;

    @Models(type = ModelsType.LIST, size = -1)
    List<Message> models;

    @Before
    public void setUp() {
        model = sut.save(model);
    }

    @Test
    public void findOne() {
        assertEquals(model, sut.findOne(model.getId()));
    }
}