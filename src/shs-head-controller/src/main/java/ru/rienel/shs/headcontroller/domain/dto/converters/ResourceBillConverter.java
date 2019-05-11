package ru.rienel.shs.headcontroller.domain.dto.converters;

import java.util.Objects;

import org.springframework.stereotype.Component;

import ru.rienel.shs.headcontroller.domain.IndicationRecord;
import ru.rienel.shs.headcontroller.domain.Person;
import ru.rienel.shs.headcontroller.domain.ResourceBill;
import ru.rienel.shs.headcontroller.domain.dto.IndicationRecordDto;
import ru.rienel.shs.headcontroller.domain.dto.PersonDto;
import ru.rienel.shs.headcontroller.domain.dto.ResourceBillDto;

@Component
public class ResourceBillConverter implements Converter<ResourceBill, ResourceBillDto> {

	private final Converter<Person, PersonDto> personConverter;
	private final Converter<IndicationRecord, IndicationRecordDto> indicationRecordConverter;

	public ResourceBillConverter(Converter<Person, PersonDto> personConverter,
	                             Converter<IndicationRecord, IndicationRecordDto> indicationRecordConverter) {
		this.personConverter = personConverter;
		this.indicationRecordConverter = indicationRecordConverter;
	}

	@Override
	public ResourceBill fromDto(ResourceBillDto dto) {
		Objects.requireNonNull(dto);

		ResourceBill resourceBill = new ResourceBill();
		resourceBill.setSerialNumber(dto.getSerialNumber());
		resourceBill.setCostPerUnit(dto.getCostPerUnit());
		resourceBill.setLastIndication(indicationRecordConverter.fromDto(dto.getLastIndication()));
		resourceBill.setPerson(personConverter.fromDto(dto.getPerson()));
		resourceBill.setSummary(dto.getSummary());
		return resourceBill;
	}

	@Override
	public ResourceBillDto fromDomain(ResourceBill resourceBill) {
		ResourceBillDto dto = new ResourceBillDto();
		dto.setSerialNumber(resourceBill.getSerialNumber());
		dto.setCostPerUnit(resourceBill.getCostPerUnit());
		dto.setLastIndication(indicationRecordConverter.fromDomain(resourceBill.getLastIndication()));
		dto.setPerson(personConverter.fromDomain(resourceBill.getPerson()));
		dto.setSummary(dto.getSummary());
		return dto;
	}
}
