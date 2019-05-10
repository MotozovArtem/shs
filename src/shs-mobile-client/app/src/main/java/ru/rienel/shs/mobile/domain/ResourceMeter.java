package ru.rienel.shs.mobile.domain;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Stack;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.ToString;

@DatabaseTable(tableName = "ResourceMeter")
@ToString(of = {"id", "serialNumber", "type"})
public class ResourceMeter {

	@DatabaseField(columnName = "id", generatedId = true)
	private Long id;

	@DatabaseField(columnName = "serial_number", canBeNull = false)
	private String serialNumber;

	@DatabaseField(columnName = "type", canBeNull = false, dataType = DataType.ENUM_STRING)
	private ResourceType type;

	@DatabaseField(columnName = "verification")
	private Stack<ZonedDateTime> verification;

	@DatabaseField(columnName = "added_time")
	private ZonedDateTime addedTime;

	@DatabaseField(columnName = "neighbors")
	private List<ResourceMeter> neighbors;

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

	public Stack<ZonedDateTime> getVerification() {
		return verification;
	}

	public void setVerification(Stack<ZonedDateTime> verification) {
		this.verification = verification;
	}

	public ZonedDateTime getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(ZonedDateTime addedTime) {
		this.addedTime = addedTime;
	}

	public List<ResourceMeter> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(List<ResourceMeter> neighbors) {
		this.neighbors = neighbors;
	}
}
