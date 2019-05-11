package ru.rienel.shs.mobile.domain.converter;

import java.util.Objects;

import ru.rienel.shs.mobile.domain.IndicationRecord;
import ru.rienel.shs.mobile.domain.dto.IndicationRecordDto;

public class IndicationRecordConverter {
	public static IndicationRecord fromDto(IndicationRecordDto indicationRecordDto) {
		Objects.requireNonNull(indicationRecordDto);
		IndicationRecord indicationRecord = new IndicationRecord();
		indicationRecord.setRecordUuid(indicationRecordDto.getRecordUuid());
		indicationRecord.setRecordDate(indicationRecordDto.getRecordDate());
		indicationRecord.setValue(indicationRecordDto.getValue());
		indicationRecord.setDelta(indicationRecordDto.getValue());
		return indicationRecord;
	}

	public static IndicationRecordDto fromDomain(IndicationRecord indicationRecord) {
		Objects.requireNonNull(indicationRecord);
		IndicationRecordDto indicationRecordDto = new IndicationRecordDto();
		indicationRecordDto.setRecordUuid(indicationRecord.getRecordUuid());
		indicationRecordDto.setRecordDate(indicationRecord.getRecordDate());
		indicationRecordDto.setValue(indicationRecord.getValue());
		indicationRecordDto.setDelta(indicationRecord.getValue());
		return indicationRecordDto;
	}
}
