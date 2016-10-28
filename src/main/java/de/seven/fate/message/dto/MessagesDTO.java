package de.seven.fate.message.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "messages")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessagesDTO implements Serializable {

    @XmlAttribute(name = "uuid", required = true)
    private String uuid;

    @XmlElement(name = "message", required = true, nillable = false)
    private List<MessageDTO> messages = new ArrayList<>();
}