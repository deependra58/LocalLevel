package com.softtech.localLevel.response;

public class RiversResponseDto {

	private String river;
	private String district;
	private String riverImage;
	private String description;
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRiverImage() {
		return riverImage;
	}

	public void setRiverImage(String riverImage) {
		this.riverImage = riverImage;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getRiver() {
		return river;
	}

	public void setRiver(String river) {
		this.river = river;
	}

	@Override
	public String toString() {
		return "RiversResponseDto [river=" + river + ", district=" + district + ", riverImage=" + riverImage
				+ ", description=" + description + "]";
	}
	
	
}
