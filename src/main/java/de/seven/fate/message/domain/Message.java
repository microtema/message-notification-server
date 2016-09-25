package de.seven.fate.message.domain;

import de.seven.fate.message.enums.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(exclude = { "id" })
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private String image;

    private Date pubDate;

    private MessageType messageType;

}
