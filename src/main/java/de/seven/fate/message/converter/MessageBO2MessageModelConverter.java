package de.seven.fate.message.converter;

import de.seven.fate.converter.AbstractModelConverter;
import de.seven.fate.message.bo.MessageBO;
import de.seven.fate.message.domain.Message;
import de.seven.fate.message.enums.MessageType;
import org.springframework.stereotype.Component;

@Component
public class MessageBO2MessageModelConverter extends AbstractModelConverter<Message, MessageBO> {

    @Override
    public void update(Message dest, MessageBO orig) {
        super.update(dest, orig);

        dest.setMessageType(MessageType.valueOf(orig.getType()));
    }
}