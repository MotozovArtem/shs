package ru.rienel.shs.mobile.domain.dto;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndicationRecordDto {

	@JsonProperty("recordUuid")
	private UUID recordUuid;

	@JsonProperty("value")
	private Double value;

	@JsonProperty("recordDate")
	private Date recordDate;

	@JsonProperty("delta")
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

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Double getDelta() {
		return delta;
	}

	public void setDelta(Double delta) {
		this.delta = delta;
	}
}
