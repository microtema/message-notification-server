package de.seven.fate.person.converter;

import de.seven.fate.converter.AbstractModelConverter;
import de.seven.fate.person.domain.Person;
import de.seven.fate.person.dto.PersonDTO;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class PersonDTO2PersonConverter extends AbstractModelConverter<Person, PersonDTO> {

}