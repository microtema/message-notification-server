package de.seven.fate.builder;

import de.seven.fate.message.dto.MessageDTO;
import de.seven.fate.model.builder.AbstractModelBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class MessageDTOBuilder extends AbstractModelBuilder<MessageDTO> {

    @Override
    public MessageDTO min() {

        MessageDTO min = new MessageDTO();

        min.setDescription(UUID.randomUUID().toString());
        min.setImage(UUID.randomUUID().toString());
        min.setPubDate(new Date());

        return min;
    }
}
