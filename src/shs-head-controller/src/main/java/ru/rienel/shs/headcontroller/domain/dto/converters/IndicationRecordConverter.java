package ru.rienel.shs.headcontroller.domain.dto.converters;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.rienel.shs.headcontroller.domain.IndicationRecord;
import ru.rienel.shs.headcontroller.domain.dto.IndicationRecordDto;
import ru.rienel.shs.headcontroller.repository.ResourceMeterRepository;
import ru.rienel.shs.headcontroller.util.Strings;

@Component
public class IndicationRecordConverter implements Converter<IndicationRecord, IndicationRecordDto> {
	private final ResourceMeterRepository resourceMeterRepository;

	@Autowired
	public IndicationRecordConverter(ResourceMeterRepository resourceMeterRepository) {
		this.resourceMeterRepository = resourceMeterRepository;
	}

	@Override
	public IndicationRecord fromDto(IndicationRecordDto dto) {
		IndicationRecord indicationRecord = new IndicationRecord();
		indicationRecord.setValue(dto.getValue());
		indicationRecord.setDevice(resourceMeterRepository.findBySerialNumber(dto.getSerialNumber()));
		indicationRecord.setRecordDate(ZonedDateTime.now());
		indicationRecord.setRecordUuid(UUID.randomUUID());
		return indicationRecord;
	}

	@Override
	public IndicationRecordDto fromDomain(IndicationRecord indicationRecord) {
		IndicationRecordDto dto = new IndicationRecordDto();
		dto.setValue(indicationRecord.getValue());
		dto.setSerialNumber(Objects.isNull(indicationRecord.getDevice()) ?
				Strings.empty() :
				indicationRecord.getDevice().getSerialNumber());
		return dto;
	}
}
