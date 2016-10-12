package de.seven.fate.message.service;

import de.seven.fate.message.dto.MessageDTO;
import de.seven.fate.message.dto.MessagesDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

@Log4j2
@Service
public class XmlMessagesService {

    @Inject
    private XmlMessageService messageService;


    public void process(MessagesDTO messagesDTO) {
        notNull(messagesDTO);

        List<MessageDTO> messageDTOList = messagesDTO.getMessages();

        log.debug("process " + messageDTOList.size() + " DTO messages.");

        messageService.process(messageDTOList);
    }

}