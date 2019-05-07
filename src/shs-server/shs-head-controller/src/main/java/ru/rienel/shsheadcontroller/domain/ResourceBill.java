package ru.rienel.shsheadcontroller.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.ToString;

@Entity(name = "ResourceBill")
@Table(name = "ResourceBill")
@ToString(of = {"id", "serialNumber", "summary", "person"})
public class ResourceBill {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "serialNumber", length = 100)
	private String serialNumber;

	@Column(name = "costPerUnit", precision = 5, scale = 2)
	private Double costPerUnit;

	@Column(name = "summary", precision = 8, scale = 2)
	private Double summary;

	@Column(name = "person")
	@ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY, optional = false)
	private Long person;

	@Column(name = "lastIndication")
	@ManyToOne(targetEntity = IndicationRecord.class, fetch = FetchType.LAZY, optional = false)
	private Long lastIndication;

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

	public Long getPerson() {
		return person;
	}

	public void setPerson(Long person) {
		this.person = person;
	}

	public Long getLastIndication() {
		return lastIndication;
	}

	public void setLastIndication(Long lastIndication) {
		this.lastIndication = lastIndication;
	}
}
