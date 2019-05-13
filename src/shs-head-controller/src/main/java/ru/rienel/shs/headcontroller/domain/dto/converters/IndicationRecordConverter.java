package ru.rienel.shs.headcontroller.domain.dto.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.rienel.shs.headcontroller.domain.IndicationRecord;
import ru.rienel.shs.headcontroller.domain.dto.IndicationRecordDto;

@Component
public class IndicationRecordConverter implements Converter<IndicationRecord, IndicationRecordDto> {
	private final ResourceMeterConverter resourceMeterConverter;

	@Autowired
	public IndicationRecordConverter(ResourceMeterConverter resourceMeterConverter) {
		this.resourceMeterConverter = resourceMeterConverter;
	}

	@Override
	public IndicationRecord fromDto(IndicationRecordDto dto) {
		IndicationRecord indicationRecord = new IndicationRecord();
		indicationRecord.setRecordUuid(dto.getRecordUuid());
		indicationRecord.setRecordDate(dto.getRecordDate());
		indicationRecord.setDelta(dto.getDelta());
		indicationRecord.setValue(dto.getValue());
		indicationRecord.setDevice(resourceMeterConverter.fromDto(dto.getDevice()));
		return indicationRecord;
	}

	@Override
	public IndicationRecordDto fromDomain(IndicationRecord indicationRecord) {
		IndicationRecordDto dto = new IndicationRecordDto();
		dto.setRecordUuid(indicationRecord.getRecordUuid());
		dto.setRecordDate(indicationRecord.getRecordDate());
		dto.setDelta(indicationRecord.getDelta());
		dto.setValue(indicationRecord.getValue());
		dto.setDevice(resourceMeterConverter.fromDomain(indicationRecord.getDevice()));
		return dto;
	}
}
