package de.seven.fate.message.converter;

import de.seven.fate.converter.AbstractModelConverter;
import de.seven.fate.message.bo.MessageBO;
import de.seven.fate.message.domain.Message;
import org.springframework.stereotype.Component;

@Component
public class Message2MessageBOModelConverter extends AbstractModelConverter<MessageBO, Message> {

    @Override
    public void update(MessageBO dest, Message orig) {
        super.update(dest, orig);

        dest.setType(orig.getMessageType().name());
    }
}