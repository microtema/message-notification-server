package de.seven.fate.event;

import de.seven.fate.message.domain.Message;

public class MessageEventData extends PersonRelatedEventData<Message> {

    public MessageEventData(String ldapId, Message data) {
        super(ldapId, data);
    }
}