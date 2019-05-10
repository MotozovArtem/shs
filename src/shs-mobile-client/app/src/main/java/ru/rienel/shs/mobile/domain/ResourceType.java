package ru.rienel.shs.mobile.domain;

public enum ResourceType {
	HOT_WATER("Горячая вода"),
	COLD_WATER("Холодная вода"),
	GAS("Газ"),
	ELECTRICITY("Электричество");

	private String name;

	ResourceType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
