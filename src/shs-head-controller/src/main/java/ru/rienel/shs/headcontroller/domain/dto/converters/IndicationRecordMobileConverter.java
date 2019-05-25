package ru.rienel.shs.headcontroller.domain.dto.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.rienel.shs.headcontroller.domain.IndicationRecord;
import ru.rienel.shs.headcontroller.domain.ResourceMeter;
import ru.rienel.shs.headcontroller.domain.dto.IndicationRecordMobileDto;
import ru.rienel.shs.headcontroller.domain.dto.ResourceMeterDto;
import ru.rienel.shs.headcontroller.repository.ResourceMeterRepository;

@Component
public class IndicationRecordMobileConverter implements Converter<IndicationRecord, IndicationRecordMobileDto> {
	@Autowired
	private ResourceMeterRepository resourceMeterRepository;

	@Autowired
	private Converter<ResourceMeter, ResourceMeterDto> converter;

	@Override
	public IndicationRecord fromDto(IndicationRecordMobileDto indicationRecordMobileDto) {
		IndicationRecord domain = new IndicationRecord();
		domain.setRecordUuid(indicationRecordMobileDto.getRecordUuid());
		domain.setRecordDate(indicationRecordMobileDto.getRecordDate());
		domain.setDelta(indicationRecordMobileDto.getDelta());
		domain.setValue(indicationRecordMobileDto.getValue());
		domain.setDevice(resourceMeterRepository.findBySerialNumber(indicationRecordMobileDto.getDevice().getSerialNumber()));
		return domain;
	}

	@Override
	public IndicationRecordMobileDto fromDomain(IndicationRecord indicationRecord) {
		IndicationRecordMobileDto dto = new IndicationRecordMobileDto();
		dto.setRecordUuid(indicationRecord.getRecordUuid());
		dto.setDelta(indicationRecord.getDelta());
		dto.setDevice(converter.fromDomain(indicationRecord.getDevice()));
		dto.setRecordDate(indicationRecord.getRecordDate());
		dto.setValue(indicationRecord.getValue());
		return dto;
	}
}
