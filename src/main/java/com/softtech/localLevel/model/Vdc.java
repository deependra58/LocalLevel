package com.softtech.localLevel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vdc {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String newVdcName;
	private String chairmen;
	private String viceChairmen;
	private Long districtId;
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNewVdcName() {
		return newVdcName;
	}
	public void setNewVdcName(String newVdcName) {
		this.newVdcName = newVdcName;
	}
	public String getChairmen() {
		return chairmen;
	}
	public void setChairmen(String chairmen) {
		this.chairmen = chairmen;
	}
	public String getViceChairmen() {
		return viceChairmen;
	}
	public void setViceChairmen(String viceChairmen) {
		this.viceChairmen = viceChairmen;
	}
	@Override
	public String toString() {
		return "Vdc [id=" + id + ", newVdcName=" + newVdcName + ", chairmen=" + chairmen + ", viceChairmen="
				+ viceChairmen + "]";
	}
	public Vdc() {
		super();
	}
	
	

}
