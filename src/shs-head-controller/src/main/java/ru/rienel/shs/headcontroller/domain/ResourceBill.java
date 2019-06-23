package ru.rienel.shs.headcontroller.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.ToString;

@Entity(name = "ResourceBill")
@Table(name = "ResourceBill")
@ToString(of = {"id", "serialNumber", "summary", "person"})
public class ResourceBill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "serial_number", length = 100, unique = true)
	private String serialNumber;

	@Column(name = "cost_per_unit", precision = 5, scale = 2)
	private Double costPerUnit;

	@Column(name = "summary", precision = 8, scale = 2)
	private Double summary;

	@JoinColumn(name = "person")
	@ManyToOne(targetEntity = Person.class, optional = false)
	private Person person;

	@JoinColumn(name = "last_indication")
	@ManyToOne(targetEntity = IndicationRecord.class, optional = false)
	private IndicationRecord lastIndication;

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
