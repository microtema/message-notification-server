package de.seven.fate.message.domain;

import de.seven.fate.message.enums.MessageType;
import de.seven.fate.person.domain.BaseEntity;
import de.seven.fate.person.domain.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(exclude = { "id" })
public class Message extends BaseEntity {

    @NotNull
    @Lob
    @Column(length = 2048)
    private String description;

    @NotNull
    private String image;

    @NotNull
    @Column(name = "PUB_DATE")
    private Date pubDate;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "MESSAGE_TYPE")
    private MessageType messageType;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Person person;

}
