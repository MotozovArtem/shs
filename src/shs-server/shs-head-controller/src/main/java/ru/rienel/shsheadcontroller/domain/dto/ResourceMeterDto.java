package ru.rienel.shsheadcontroller.domain.dto;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Stack;

import lombok.ToString;

import ru.rienel.shsheadcontroller.domain.ResourceMeter;
import ru.rienel.shsheadcontroller.domain.ResourceType;

@ToString(of = {"serialNumber", "type"})
public class ResourceMeterDto {

	private String serialNumber;

	private ResourceType type;

	private Stack<ZonedDateTime> verification;

	private ZonedDateTime addedTime;

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
