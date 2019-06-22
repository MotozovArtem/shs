package ru.rienel.shs.mobile.domain;

import lombok.ToString;

@ToString(of = {"personCount", "metersCount", "indicationsCount", "billsCount"})
public class Common {

	private Long personCount;

	private Long metersCount;

	private Long indicationsCount;

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
