package ru.rienel.shsheadcontroller.domain.dto.converters;

import java.util.Objects;

import ru.rienel.shsheadcontroller.domain.ResourceBill;
import ru.rienel.shsheadcontroller.domain.dto.ResourceBillDto;

public class ResourceBillConverter {

	private PersonConverter personConverter;
	private IndicationRecordConverter indicationRecordConverter;

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

	public ResourceBillDto toDto(ResourceBill resourceBill) {
		ResourceBillDto dto = new ResourceBillDto();
		dto.setSerialNumber(resourceBill.getSerialNumber());
		dto.setCostPerUnit(resourceBill.getCostPerUnit());
		dto.setLastIndication(indicationRecordConverter.toDto(resourceBill.getLastIndication()));
		dto.setPerson(personConverter.toDto(resourceBill.getPerson()));
		dto.setSummary(dto.getSummary());
		return dto;
	}
}
