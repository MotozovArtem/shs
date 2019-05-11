package ru.rienel.shs.mobile.domain.converter;

import java.util.Objects;

import ru.rienel.shs.mobile.domain.Person;
import ru.rienel.shs.mobile.domain.dto.PersonDto;

public class PersonConverter {
	public static Person fromDto(PersonDto dto) {
		Objects.requireNonNull(dto);

		Person person = new Person();
		person.setName(dto.getName());
		person.setSurname(dto.getSurname());
		person.setPatronymic(dto.getPatronymic());
		person.setAddress(dto.getAddress());
		return person;
	}

	public static PersonDto fromDomain(Person person) {
		Objects.requireNonNull(person);

		PersonDto personDto = new PersonDto();
		personDto.setName(person.getName());
		personDto.setSurname(person.getSurname());
		personDto.setPatronymic(person.getPatronymic());
		personDto.setAddress(person.getAddress());
		return personDto;
	}
}
