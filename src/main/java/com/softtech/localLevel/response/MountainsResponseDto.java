package com.softtech.localLevel.response;

import com.softtech.localLevel.model.District;

public class MountainsResponseDto {

	private String mountain;
	private String mountainHeight;
	private String district;

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getMountain() {
		return mountain;
	}

	public void setMountain(String mountain) {
		this.mountain = mountain;
	}

	public String getMountainHeight() {
		return mountainHeight;
	}

	public void setMountainHeight(String mountainHeight) {
		this.mountainHeight = mountainHeight;
	}

}
