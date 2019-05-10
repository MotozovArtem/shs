package ru.rienel.shs.mobile.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceBillDto {
	@JsonProperty("serialNumber")
	private String serialNumber;

	@JsonProperty("costPerUnit")
	private Double costPerUnit;

	@JsonProperty("summary")
	private Double summary;

	@JsonProperty("person")
	private PersonDto person;

	@JsonProperty("lastIndication")
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
