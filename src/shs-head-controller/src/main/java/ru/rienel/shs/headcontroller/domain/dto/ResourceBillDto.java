package ru.rienel.shs.headcontroller.domain.dto;

import lombok.ToString;

@ToString(of = {"serialNumber", "person"})
public class ResourceBillDto {

	private String serialNumber;

	private Double costPerUnit;

	private Double summary;

	private PersonDto person;

	private IndicationRecordDto lastIndication;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Double getCostPerUnit() {
		return costPerUnit;
	}

	public void setCostPerUnit(Double costPerUnit) {
		this.costPerUnit = costPerUnit;
	}

	public Double getSummary() {
		return summary;
	}

	public void setSummary(Double summary) {
		this.summary = summary;
	}

	public PersonDto getPerson() {
		return person;
	}

	public void setPerson(PersonDto person) {
		this.person = person;
	}

	public IndicationRecordDto getLastIndication() {
		return lastIndication;
	}

	public void setLastIndication(IndicationRecordDto lastIndication) {
		this.lastIndication = lastIndication;
	}
}
