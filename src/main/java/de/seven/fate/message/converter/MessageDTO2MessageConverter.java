package de.seven.fate.message.converter;

import de.seven.fate.converter.AbstractModelConverter;
import de.seven.fate.message.domain.Message;
import de.seven.fate.message.dto.MessageDTO;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.person.converter.PersonDTO2PersonConverter;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static org.apache.commons.lang3.Validate.notNull;

@Component
public class MessageDTO2MessageConverter extends AbstractModelConverter<Message, MessageDTO> {

    private final PersonDTO2PersonConverter personDTO2PersonConverter;

    @Inject
    public MessageDTO2MessageConverter(PersonDTO2PersonConverter personDTO2PersonConverter) {
        this.personDTO2PersonConverter = personDTO2PersonConverter;
    }

    @Override
    public void update(Message dest, MessageDTO orig) {
        notNull(dest, "dest should not be null");
        if (orig == null) {
            return;
        }

        dest.setDescription(orig.getDescription());
        dest.setImage(orig.getImage());
        dest.setTitle(orig.getTitle());
        dest.setPubDate(orig.getPubDate());

        dest.setMessageType(MessageType.UNREAD);

        dest.setPerson(personDTO2PersonConverter.convert(orig.getPerson()));
    }
}