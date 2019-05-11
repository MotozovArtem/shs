package ru.rienel.shs.mobile.domain.converter;

import java.util.Objects;

import ru.rienel.shs.mobile.domain.ResourceMeter;
import ru.rienel.shs.mobile.domain.dto.ResourceMeterDto;

public class ResourceMeterConverter {
	public static ResourceMeter fromDto(ResourceMeterDto dto) {
		Objects.requireNonNull(dto);
		ResourceMeter domain = new ResourceMeter();
		domain.setSerialNumber(dto.getSerialNumber());
		domain.setAddedTime(dto.getAddedTime());
		domain.setNeighbors(dto.getNeighbors());
		domain.setType(dto.getType());
		domain.setVerification(dto.getVerification());
		return domain;
	}

	public static ResourceMeterDto fromDomain(ResourceMeter domain) {
		Objects.requireNonNull(domain);
		ResourceMeterDto dto = new ResourceMeterDto();
		dto.setSerialNumber(domain.getSerialNumber());
		dto.setAddedTime(domain.getAddedTime());
		dto.setNeighbors(domain.getNeighbors());
		dto.setType(domain.getType());
		dto.setVerification(domain.getVerification());
		return dto;
	}
}
