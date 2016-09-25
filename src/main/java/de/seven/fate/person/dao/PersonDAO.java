package de.seven.fate.person.dao;

import de.seven.fate.person.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonDAO extends CrudRepository<Person, Long> {
}
