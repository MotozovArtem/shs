package ru.rienel.shsheadcontroller.domain.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.ToString;

@ToString(of = {"recordUuid", "value", "recordDate"})
public class IndicationRecordDto {

	private UUID recordUuid;

	private Double value;

	private ZonedDateTime recordDate;

	private Double delta;

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
}
