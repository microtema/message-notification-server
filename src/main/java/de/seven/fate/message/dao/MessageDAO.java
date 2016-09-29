package de.seven.fate.message.dao;

import de.seven.fate.message.domain.Message;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.person.domain.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MessageDAO extends CrudRepository<Message, Long> {

    List<Message> findByPersonLdapId(String ldapId);

    //@Query("SELECT m FROM Message m WHERE m.person.ldapId = ?1 AND m.messageType = ?2")
    List<Message> findByPersonLdapIdAndMessageType(String ldapId, MessageType messageType);

    Message findFirstByPerson(Person person);

    @Modifying
    @Query("UPDATE Message m SET m.messageType = ?2 WHERE m.id IN (?1)")
    int markMessage(List<Long> messageIds, MessageType messageType);
}
