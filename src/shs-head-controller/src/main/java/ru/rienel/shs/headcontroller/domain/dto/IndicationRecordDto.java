package ru.rienel.shs.headcontroller.domain.dto;

import lombok.ToString;

@ToString(of = {"value", "serialNumber"})
public class IndicationRecordDto {

	private Double value;

	private String serialNumber;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
