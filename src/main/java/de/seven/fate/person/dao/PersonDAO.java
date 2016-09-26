package de.seven.fate.person.dao;

import de.seven.fate.person.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends CrudRepository<Person, Long> {

    Person findByLdapId(String personLdapId);
}
