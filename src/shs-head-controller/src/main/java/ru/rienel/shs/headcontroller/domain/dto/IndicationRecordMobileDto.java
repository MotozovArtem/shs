package ru.rienel.shs.headcontroller.domain.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"recordUuid", "value", "recordDate", "device"})
public class IndicationRecordMobileDto {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("recordUuid")
	private UUID recordUuid;

	@JsonProperty("value")
	private Double value;

	@JsonProperty("recordDate")
	private ZonedDateTime recordDate;

	@JsonProperty("delta")
	private Double delta;

	@JsonProperty("device")
	private ResourceMeterDto device;

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
