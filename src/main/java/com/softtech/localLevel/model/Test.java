package com.softtech.localLevel.model;

import java.io.Serializable;

public class Test implements Serializable{
	private String state;
	private String district;
	private String newvdc;
	private String oldvdc;
	private String oldward;
	private String newward;
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
	public String getNewvdc() {
		return newvdc;
	}
	public void setNewvdc(String newvdc) {
		this.newvdc = newvdc;
	}
	public String getOldvdc() {
		return oldvdc;
	}
	public void setOldvdc(String oldvdc) {
		this.oldvdc = oldvdc;
	}
	public String getOldward() {
		return oldward;
	}
	public void setOldward(String oldward) {
		this.oldward = oldward;
	}
	public String getNewward() {
		return newward;
	}
	public void setNewward(String newward) {
		this.newward = newward;
	}
	
	
}
