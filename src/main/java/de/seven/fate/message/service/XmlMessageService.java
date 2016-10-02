package de.seven.fate.message.service;

import de.seven.fate.message.Constants;
import de.seven.fate.message.converter.MessageDTO2MessageConverter;
import de.seven.fate.message.domain.Message;
import de.seven.fate.message.dto.MessageDTO;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

@Service
public class XmlMessageService {

    private static final Logger logger = LoggerFactory.getLogger(XmlMessageService.class);


    @Inject
    private MessageService service;

    @Inject
    private MessageDTO2MessageConverter converter;

    public void process(List<MessageDTO> messageDTOList) {
        notNull(messageDTOList);

        logger.debug("precess " + messageDTOList.size() + " DTO messages.");

        List<Message> messages = converter.convertList(messageDTOList);

        logger.debug("convert " + messages.size() + " messages.");

        service.saveMessage(messages);
    }

}