package ru.rienel.shs.headcontroller.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString(of = {"personCount", "metersCount", "indicationsCount", "billsCount"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Common {

	@JsonProperty("personCount")
	private Long personCount;

	@JsonProperty("metersCount")
	private Long metersCount;

	@JsonProperty("indicationsCount")
	private Long indicationsCount;

	@JsonProperty("billsCount")
	private Long billsCount;

	public Long getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Long personCount) {
		this.personCount = personCount;
	}

	public Long getMetersCount() {
		return metersCount;
	}

	public void setMetersCount(Long metersCount) {
		this.metersCount = metersCount;
	}

	public Long getIndicationsCount() {
		return indicationsCount;
	}

	public void setIndicationsCount(Long indicationsCount) {
		this.indicationsCount = indicationsCount;
	}

	public Long getBillsCount() {
		return billsCount;
	}

	public void setBillsCount(Long billsCount) {
		this.billsCount = billsCount;
	}
}
