package com.softtech.localLevel.response;

public class MountainsResponseDto {

	private String mountain;
	private String mountainHeight;
	private String district;
	private String description;
	private String mountainImage;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMountainImage() {
		return mountainImage;
	}

	public void setMountainImage(String mountainImage) {
		this.mountainImage = mountainImage;
	}

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

	@Override
	public String toString() {
		return "MountainsResponseDto [mountain=" + mountain + ", mountainHeight=" + mountainHeight + ", district="
				+ district + ", description=" + description + ", mountainImage=" + mountainImage + "]";
	}

}
