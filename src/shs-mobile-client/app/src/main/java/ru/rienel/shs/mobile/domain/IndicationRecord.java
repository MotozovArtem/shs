package ru.rienel.shs.mobile.domain;

import java.util.Date;
import java.util.UUID;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.ToString;

@DatabaseTable(tableName = "IndicationRecord")
@ToString(of = {"id", "recordUuid", "recordDate"})
public class IndicationRecord {

	@DatabaseField(columnName = "id")
	private Long id;

	@DatabaseField(columnName = "record_uuid", dataType = DataType.UUID, canBeNull = false)
	private UUID recordUuid;

	@DatabaseField(columnName = "value", canBeNull = false)
	private Double value;

	@DatabaseField(columnName = "record_date", dataType = DataType.DATE)
	private Date recordDate;

	@DatabaseField(columnName = "device", foreign = true, canBeNull = false)
	private ResourceMeter device;

	@DatabaseField(columnName = "delta")
	private Double delta;

	public IndicationRecord() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getRecordUuid() {
		return recordUuid;
	}

	public void setRecordUuid(UUID recordUuid) {
		this.recordUuid = recordUuid;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public ResourceMeter getDevice() {
		return device;
	}

	public void setDevice(ResourceMeter device) {
		this.device = device;
	}

	public Double getDelta() {
		return delta;
	}

	public void setDelta(Double delta) {
		this.delta = delta;
	}
}
