package ru.rienel.shs.headcontroller.domain.dto.converters;

import org.springframework.stereotype.Component;

import ru.rienel.shs.headcontroller.domain.IndicationRecord;
import ru.rienel.shs.headcontroller.domain.dto.IndicationRecordDto;

@Component
public class IndicationRecordConverter implements Converter<IndicationRecord, IndicationRecordDto> {

	@Override
	public IndicationRecord fromDto(IndicationRecordDto dto) {
		IndicationRecord indicationRecord = new IndicationRecord();
		indicationRecord.setRecordUuid(dto.getRecordUuid());
		indicationRecord.setRecordDate(dto.getRecordDate());
		indicationRecord.setDelta(dto.getDelta());
		indicationRecord.setValue(dto.getValue());
		return indicationRecord;
	}

	@Override
	public IndicationRecordDto fromDomain(IndicationRecord indicationRecord) {
		IndicationRecordDto dto = new IndicationRecordDto();
		dto.setRecordUuid(indicationRecord.getRecordUuid());
		dto.setRecordDate(indicationRecord.getRecordDate());
		dto.setDelta(indicationRecord.getDelta());
		dto.setValue(indicationRecord.getValue());
		return dto;
	}
}
