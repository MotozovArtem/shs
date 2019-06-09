package ru.rienel.shs.headcontroller.domain.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.persistence.AttributeConverter;

public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Long> {

	@Override
	public Long convertToDatabaseColumn(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return 0L;
		}
		return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Long aLong) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(aLong), ZoneOffset.UTC);
	}
}
