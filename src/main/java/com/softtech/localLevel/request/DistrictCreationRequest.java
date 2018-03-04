package com.softtech.localLevel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DistrictCreationRequest implements Serializable {
	
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String district;
	private Long stateId;
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
}
