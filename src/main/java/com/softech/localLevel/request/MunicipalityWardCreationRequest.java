package com.softech.localLevel.request;

public class MunicipalityWardCreationRequest {

	private String municipality;
	private Long newWardId;
	private String newWardName;
	private Long oldWardId;
	private String oldWardName;
	private String oldVdcName;

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
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
