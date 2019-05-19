package ru.rienel.shs.headcontroller.domain.converter;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import javax.persistence.AttributeConverter;

public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Long> {

	@Override
	public Long convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
		return zonedDateTime.toInstant().toEpochMilli();
	}

	@Override
	public ZonedDateTime convertToEntityAttribute(Long aLong) {
		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(aLong), ZoneOffset.UTC);
	}
}
