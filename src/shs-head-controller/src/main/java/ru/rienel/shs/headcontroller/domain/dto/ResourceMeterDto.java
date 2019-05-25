package ru.rienel.shs.headcontroller.domain.dto;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Stack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import ru.rienel.shs.headcontroller.domain.ResourceMeter;
import ru.rienel.shs.headcontroller.domain.ResourceType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = {"serialNumber", "type"})
public class ResourceMeterDto {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("serialNumber")
	private String serialNumber;

	@JsonProperty("type")
	private ResourceType type;

	@JsonProperty("verification")
	private Stack<ZonedDateTime> verification;

	@JsonProperty("addedTime")
	private ZonedDateTime addedTime;

	@JsonProperty("neighbors")
	private List<ResourceMeter> neighbors;

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
