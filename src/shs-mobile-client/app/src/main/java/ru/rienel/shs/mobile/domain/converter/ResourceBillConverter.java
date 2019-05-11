package ru.rienel.shs.mobile.domain.converter;

import java.util.Objects;

import ru.rienel.shs.mobile.domain.ResourceBill;
import ru.rienel.shs.mobile.domain.dto.ResourceBillDto;

public class ResourceBillConverter {
	public static ResourceBill fromDto(ResourceBillDto dto) {
		Objects.requireNonNull(dto);
		ResourceBill domain = new ResourceBill();
		domain.setSerialNumber(dto.getSerialNumber());
		domain.setCostPerUnit(dto.getCostPerUnit());
		domain.setSummary(dto.getSummary());
		domain.setLastIndication(IndicationRecordConverter.fromDto(dto.getLastIndication()));
		domain.setPerson(PersonConverter.fromDto(dto.getPerson()));
		return domain;
	}

	public static ResourceBillDto fromDomain(ResourceBill domain) {
		Objects.requireNonNull(domain);
		ResourceBillDto dto = new ResourceBillDto();
		dto.setSerialNumber(domain.getSerialNumber());
		dto.setCostPerUnit(domain.getCostPerUnit());
		dto.setSummary(domain.getSummary());
		dto.setLastIndication(IndicationRecordConverter.fromDomain(domain.getLastIndication()));
		dto.setPerson(PersonConverter.fromDomain(domain.getPerson()));
		return dto;
	}
}
