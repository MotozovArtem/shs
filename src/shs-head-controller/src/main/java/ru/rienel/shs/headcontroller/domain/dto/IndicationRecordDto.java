package ru.rienel.shs.headcontroller.domain.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.ToString;

import ru.rienel.shs.headcontroller.domain.ResourceMeter;

@ToString(of = {"recordUuid", "value", "recordDate"})
public class IndicationRecordDto {

	private UUID recordUuid;

	private Double value;

	private ZonedDateTime recordDate;

	private Double delta;

	private ResourceMeterDto device;

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

	public ZonedDateTime getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(ZonedDateTime recordDate) {
		this.recordDate = recordDate;
	}

	public Double getDelta() {
		return delta;
	}

	public void setDelta(Double delta) {
		this.delta = delta;
	}

	public ResourceMeterDto getDevice() {
		return device;
	}

	public void setDevice(ResourceMeterDto device) {
		this.device = device;
	}
}
