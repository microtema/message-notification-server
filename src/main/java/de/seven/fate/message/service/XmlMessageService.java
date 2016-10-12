package de.seven.fate.message.service;

import de.seven.fate.message.converter.MessageDTO2MessageConverter;
import de.seven.fate.message.domain.Message;
import de.seven.fate.message.dto.MessageDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

@Log4j2
@Service
public class XmlMessageService {

    @Inject
    private MessageService service;

    @Inject
    private MessageDTO2MessageConverter converter;

    public void process(List<MessageDTO> messageDTOList) {
        notNull(messageDTOList);

        log.debug("precess " + messageDTOList.size() + " DTO messages.");

        List<Message> messages = converter.convertList(messageDTOList);

        log.debug("convert " + messages.size() + " messages.");

        service.saveMessage(messages);
    }

}