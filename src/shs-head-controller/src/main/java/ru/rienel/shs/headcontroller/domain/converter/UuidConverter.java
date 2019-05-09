package ru.rienel.shs.headcontroller.domain.converter;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ru.rienel.shs.headcontroller.util.Strings;

@Converter
public class UuidConverter implements AttributeConverter<UUID, String> {
	@Override
	public String convertToDatabaseColumn(UUID uuid) {
		if (Objects.isNull(uuid)) {
			return Strings.empty();
		}
		return uuid.toString();
	}

	@Override
	public UUID convertToEntityAttribute(String s) {
		if (Strings.isNullOrEmpty(s)) {
			return null;
		}
		return UUID.fromString(s);
	}
}
