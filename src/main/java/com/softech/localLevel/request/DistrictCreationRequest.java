package com.softech.localLevel.request;

public class DistrictCreationRequest {
	private String area;
	private String population;
	private String headquater;
	private Long stateId;
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
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public DistrictCreationRequest() {
		super();
	}
	
}
