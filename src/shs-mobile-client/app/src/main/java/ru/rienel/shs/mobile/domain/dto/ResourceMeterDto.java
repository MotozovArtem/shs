package ru.rienel.shs.mobile.domain.dto;

import java.util.Date;
import java.util.List;
import java.util.Stack;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ru.rienel.shs.mobile.domain.ResourceMeter;
import ru.rienel.shs.mobile.domain.ResourceType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceMeterDto {
	@JsonProperty("serialNumber")
	private String serialNumber;

	@JsonProperty("type")
	private ResourceType type;

	@JsonProperty("verification")
	private Stack<Date> verification;

	@JsonProperty("addedTime")
	private Date addedTime;

	@JsonProperty("neighbors")
	private List<ResourceMeter> neighbors;

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

	public Stack<Date> getVerification() {
		return verification;
	}

	public void setVerification(Stack<Date> verification) {
		this.verification = verification;
	}

	public Date getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(Date addedTime) {
		this.addedTime = addedTime;
	}

	public List<ResourceMeter> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(List<ResourceMeter> neighbors) {
		this.neighbors = neighbors;
	}
}
