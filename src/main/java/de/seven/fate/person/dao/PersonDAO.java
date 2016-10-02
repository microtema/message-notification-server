package de.seven.fate.person.dao;

import de.seven.fate.person.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PersonDAO extends CrudRepository<Person, Long> {

    Person findByLdapId(String personLdapId);
}
