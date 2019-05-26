package ru.rienel.shs.mobile.domain;

import java.util.ArrayList;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.ToString;

@DatabaseTable(tableName = "ResourceMeter")
@ToString(of = {"id", "serialNumber", "type"})
public class ResourceMeter {

	@DatabaseField(columnName = "id", id = true)
	private Long id;

	@DatabaseField(columnName = "serial_number", canBeNull = false)
	private String serialNumber;

	@DatabaseField(columnName = "type", canBeNull = false, dataType = DataType.ENUM_STRING)
	private ResourceType type;

	@DatabaseField(columnName = "verification", dataType = DataType.SERIALIZABLE)
	private ArrayList<Date> verification;

	@DatabaseField(columnName = "added_time", dataType = DataType.DATE)
	private Date addedTime;

	@DatabaseField(columnName = "neighbors", dataType = DataType.SERIALIZABLE)
	private ArrayList<ResourceMeter> neighbors;

	public ResourceMeter() {
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

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public ArrayList<Date> getVerification() {
		return verification;
	}

	public void setVerification(ArrayList<Date> verification) {
		this.verification = verification;
	}

	public Date getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(Date addedTime) {
		this.addedTime = addedTime;
	}

	public ArrayList<ResourceMeter> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(ArrayList<ResourceMeter> neighbors) {
		this.neighbors = neighbors;
	}
}
