package com.softech.localLevel.request;

public class RuralMunicipalityWardEditRequest {

	private String ruralMunicipality;
	private Long newWardId;
	private String newWardName;
	private Long oldWardId;
	private String oldWardName;
	private String oldVdcName;
	
	public String getRuralMunicipality() {
		return ruralMunicipality;
	}
	public void setRuralMunicipality(String ruralMunicipality) {
		this.ruralMunicipality = ruralMunicipality;
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
	public String getOldVdcName() {
		return oldVdcName;
	}
	public void setOldVdcName(String oldVdcName) {
		this.oldVdcName = oldVdcName;
	}
	
}
