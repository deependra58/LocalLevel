package com.softtech.localLevel.response;

public class HydropowerResponseDto {
	
	private String hydropower;
	private String district;
	private String capacity;
	
	
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getHydropower() {
		return hydropower;
	}
	public void setHydropower(String hydropower) {
		this.hydropower = hydropower;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
}
