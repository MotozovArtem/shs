package ru.rienel.shs.headcontroller.domain.dto.converters;

import org.springframework.stereotype.Component;

import ru.rienel.shs.headcontroller.domain.ResourceMeter;
import ru.rienel.shs.headcontroller.domain.dto.ResourceMeterDto;

@Component
public class ResourceMeterConverter implements Converter<ResourceMeter, ResourceMeterDto> {

	@Override
	public ResourceMeter fromDto(ResourceMeterDto dto) {
		ResourceMeter resourceMeter = new ResourceMeter();
		resourceMeter.setSerialNumber(dto.getSerialNumber());
		resourceMeter.setType(dto.getType());
		resourceMeter.setVerification(dto.getVerification());
		resourceMeter.setAddedTime(dto.getAddedTime());
		resourceMeter.setNeighbors(dto.getNeighbors());
		return resourceMeter;
	}

	@Override
	public ResourceMeterDto fromDomain(ResourceMeter resourceMeter) {
		ResourceMeterDto dto = new ResourceMeterDto();
		dto.setSerialNumber(resourceMeter.getSerialNumber());
		dto.setType(resourceMeter.getType());
		dto.setVerification(resourceMeter.getVerification());
		dto.setAddedTime(resourceMeter.getAddedTime());
		dto.setNeighbors(resourceMeter.getNeighbors());
		return dto;
	}
}
