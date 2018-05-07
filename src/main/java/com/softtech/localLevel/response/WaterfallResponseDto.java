package com.softtech.localLevel.response;

public class WaterfallResponseDto {

	private String waterfall;
	private String description;
	private String waterfallImage;
	private String district;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWaterfallImage() {
		return waterfallImage;
	}

	public void setWaterfallImage(String waterfallImage) {
		this.waterfallImage = waterfallImage;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWaterfall() {
		return waterfall;
	}

	public void setWaterfall(String waterfall) {
		this.waterfall = waterfall;
	}

}
