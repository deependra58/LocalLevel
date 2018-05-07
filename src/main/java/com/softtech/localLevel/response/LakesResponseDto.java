package com.softtech.localLevel.response;

public class LakesResponseDto {

	
	private String lake;
	private String descriptionString;
	private String lakeImageString;
	

	public String getDescriptionString() {
		return descriptionString;
	}

	public void setDescriptionString(String descriptionString) {
		this.descriptionString = descriptionString;
	}

	public String getLakeImageString() {
		return lakeImageString;
	}

	public void setLakeImageString(String lakeImageString) {
		this.lakeImageString = lakeImageString;
	}

	private String district;
	
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLake() {
		return lake;
	}

	public void setLake(String lake) {
		this.lake = lake;
	}
	
}
