package ru.rienel.shs.mobile.domain.converter;

import java.util.Objects;

import ru.rienel.shs.mobile.domain.User;
import ru.rienel.shs.mobile.domain.dto.UserDto;

public class UserConverter {
	public static User fromDto(UserDto dto) {
		Objects.requireNonNull(dto);
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setPerson(PersonConverter.fromDto(dto.getPerson()));
		return user;
	}

	public static UserDto fromDomain(User domain) {
		UserDto dto = new UserDto();
		dto.setUsername(domain.getUsername());
		dto.setPassword(domain.getPassword());
		dto.setPerson(PersonConverter.fromDomain(domain.getPerson()));
		return dto;
	}
}
