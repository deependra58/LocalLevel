package com.softtech.localLevel.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RuralMunicipalWardResponse {
	private Long id;
	private String ruralMunicipalityName;
	private Long newWardId;
	private String newWardName;
	private Long oldWardId;
	private String oldWardName;
	private String district;
	private String OldVdcName;
	
	public String getOldVdcName() {
		return OldVdcName;
	}
	public void setOldVdcName(String oldVdcName) {
		OldVdcName = oldVdcName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRuralMunicipalityName() {
		return ruralMunicipalityName;
	}
	public void setRuralMunicipalityName(String ruralMunicipalityName) {
		this.ruralMunicipalityName = ruralMunicipalityName;
	}
	public Long getNewWardId() {
		return newWardId;
	}
	public void setNewWardId(Long newWardId) {
		this.newWardId = newWardId;
	}
	public String getNewWardName() {
		return newWardName;
	}
	public void setNewWardName(String newWardName) {
		this.newWardName = newWardName;
	}
	public Long getOldWardId() {
		return oldWardId;
	}
	public void setOldWardId(Long oldWardId) {
		this.oldWardId = oldWardId;
	}
	public String getOldWardName() {
		return oldWardName;
	}
	public void setOldWardName(String oldWardName) {
		this.oldWardName = oldWardName;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	

}
