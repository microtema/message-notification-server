package de.seven.fate.builder;

import de.seven.fate.message.domain.Message;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.model.builder.AbstractModelBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Override
    public List<Message> fixList() {

        List<Message> messages = super.fixList();

        IntStream.range(0, messages.size()).forEach((index) -> {
            messages.get(index).setMessageType(index % 2 == 0 ? MessageType.READ : MessageType.UNREAD);
        });

        return messages;
    }

    @Override
    public Set<Message> fixSet() {

        return fixList().stream().collect(Collectors.toSet());
    }
}
