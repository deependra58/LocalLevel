package com.softech.localLevel.request;

public class DistrictEditRequest {
	private String area;
	private String population;
	private String headquater;
	private String state;
	private String district;
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getHeadquater() {
		return headquater;
	}
	public void setHeadquater(String headquater) {
		this.headquater = headquater;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public DistrictEditRequest() {
		super();
	}
}
