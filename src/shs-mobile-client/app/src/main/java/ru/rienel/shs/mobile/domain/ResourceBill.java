package ru.rienel.shs.mobile.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.ToString;

@DatabaseTable(tableName = "ResourceBill")
@ToString(of = {"id", "serialNumber", "summary"})
public class ResourceBill {

	@DatabaseField(columnName = "id", id = true)
	private Long id;

	@DatabaseField(columnName = "serial_number", canBeNull = false, width = 100)
	private String serialNumber;

	@DatabaseField(columnName = "cost_per_unit")
	private Double costPerUnit;

	@DatabaseField(columnName = "summary")
	private Double summary;

	@DatabaseField(columnName = "person", foreign = true, foreignAutoRefresh = true)
	private Person person;

	@DatabaseField(columnName = "last_indication", foreign = true, foreignAutoRefresh = true)
	private IndicationRecord lastIndication;

	public ResourceBill() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public IndicationRecord getLastIndication() {
		return lastIndication;
	}

	public void setLastIndication(IndicationRecord lastIndication) {
		this.lastIndication = lastIndication;
	}
}
