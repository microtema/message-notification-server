package de.seven.fate.builder;

import de.seven.fate.message.bo.MessageBO;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.model.builder.AbstractModelBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class MessageBOBuilder extends AbstractModelBuilder<MessageBO> {

    @Override
    public MessageBO min() {
        MessageBO min = new MessageBO();

        min.setDescription(UUID.randomUUID().toString());
        min.setImage(UUID.randomUUID().toString());
        min.setPubDate(new Date());
        min.setType(MessageType.UNREAD.name());

        return min;
    }
}
