package de.seven.fate.builder;

import de.seven.fate.message.domain.Message;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.model.builder.AbstractModelBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class MessageBuilder extends AbstractModelBuilder<Message> {

    @Override
    public Message min() {
        Message min = new Message();

        min.setDescription(UUID.randomUUID().toString());
        min.setImage(UUID.randomUUID().toString());
        min.setTitle(UUID.randomUUID().toString());
        min.setPubDate(new Date());
        min.setMessageType(MessageType.UNREAD);

        return min;
    }
}
