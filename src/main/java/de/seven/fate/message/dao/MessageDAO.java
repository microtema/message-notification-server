package de.seven.fate.message.dao;

import de.seven.fate.message.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageDAO extends CrudRepository<Message, Long> {
}
