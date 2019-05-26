package ru.rienel.shs.mobile.domain;

import ru.rienel.shs.mobile.R;

public enum ResourceType {
	HOT_WATER(R.string.hotWater, R.drawable.hot_water64, R.drawable.hot_water128),
	COLD_WATER(R.string.coldWater, R.drawable.cold_water64, R.drawable.cold_water128),
	GAS(R.string.gas, R.drawable.gas64, R.drawable.gas128),
	ELECTRICITY(R.string.electricity, R.drawable.flash64, R.drawable.flash128);

	private int labelResource;

	private int iconResource64;

	private int iconResource128;

	ResourceType(int labelResource, int iconResource64, int iconResource128) {
		this.labelResource = labelResource;
		this.iconResource64 = iconResource64;
		this.iconResource128 = iconResource128;
	}

	public int getLabelResource() {
		return labelResource;
	}

	public void setLabelResource(int labelResource) {
		this.labelResource = labelResource;
	}

	public int getIconResource64() {
		return iconResource64;
	}

	public void setIconResource64(int iconResource64) {
		this.iconResource64 = iconResource64;
	}

	public int getIconResource128() {
		return iconResource128;
	}

	public void setIconResource128(int iconResource128) {
		this.iconResource128 = iconResource128;
	}
}
