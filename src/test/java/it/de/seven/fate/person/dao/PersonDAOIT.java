package it.de.seven.fate.person.dao;

import de.seven.fate.Application;
import de.seven.fate.annotation.Model;
import de.seven.fate.annotation.Models;
import de.seven.fate.person.dao.PersonDAO;
import de.seven.fate.person.domain.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@Transactional
public class PersonDAOIT {

    @Inject
    PersonDAO sut;

    @Model
    Person model;

    @Models
    List<Person> models;

    @Before
    public void setUp() {
        model = sut.save(model);
    }

    @Test
    public void findOne() {
        assertEquals(model, sut.findOne(model.getId()));
    }

    @Test
    public void findByLdapId() {
        assertEquals(model, sut.findByLdapId(model.getLdapId()));
    }

    @After
    public void tearDown() throws Exception {
        sut.deleteAll();
    }

}