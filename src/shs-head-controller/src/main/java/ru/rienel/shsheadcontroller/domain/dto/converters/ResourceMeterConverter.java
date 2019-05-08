package ru.rienel.shsheadcontroller.domain.dto.converters;

import ru.rienel.shsheadcontroller.domain.ResourceMeter;
import ru.rienel.shsheadcontroller.domain.dto.ResourceMeterDto;

public class ResourceMeterConverter {
	public ResourceMeter fromDto(ResourceMeterDto dto) {
		ResourceMeter resourceMeter = new ResourceMeter();
		resourceMeter.setSerialNumber(dto.getSerialNumber());
		resourceMeter.setType(dto.getType());
		resourceMeter.setVerification(dto.getVerification());
		resourceMeter.setAddedTime(dto.getAddedTime());
		resourceMeter.setNeighbors(dto.getNeighbors());
		return resourceMeter;
	}

	public ResourceMeterDto toDto(ResourceMeter resourceMeter) {
		ResourceMeterDto dto = new ResourceMeterDto();
		dto.setSerialNumber(resourceMeter.getSerialNumber());
		dto.setType(resourceMeter.getType());
		dto.setVerification(resourceMeter.getVerification());
		dto.setAddedTime(resourceMeter.getAddedTime());
		dto.setNeighbors(resourceMeter.getNeighbors());
		return dto;
	}
}
