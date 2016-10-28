package de.seven.fate.builder;

import de.seven.fate.message.domain.Message;
import de.seven.fate.message.enums.MessageType;
import de.seven.fate.model.builder.AbstractModelBuilder;
import de.seven.fate.person.domain.Person;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
public class PersonBuilder extends AbstractModelBuilder<Person> {

    private final MessageBuilder messageBuilder;

    @Inject
    public PersonBuilder(MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

    @Override
    public Person min() {
        Person min = new Person();

        min.setLdapId(UUID.randomUUID().toString());
        min.setMessages(messageBuilder.list());

        min.getMessages().forEach(message -> message.setPerson(min));

        return min;
    }

    @Override
    public Person fix() {
        Person min = min();

        min.setLdapId("mtema");
        min.setMessages(messageBuilder.fixList());

        min.getMessages().forEach(message -> message.setPerson(min));

        return min;
    }
}
