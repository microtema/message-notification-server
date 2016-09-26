package de.seven.fate.message.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageBO implements Serializable {

    private Long id;

    private String description;

    private String image;

    private Date pubDate;

    private String type;

    private String title;
}
