package com.softtech.localLevel.response;

import com.softtech.localLevel.util.LocalLevelType;

public class VdcResponse {

	private String newVdc;
	private LocalLevelType localLevelType;
	public String getNewVdc() {
		return newVdc;
	}
	public void setNewVdc(String newVdc) {
		this.newVdc = newVdc;
	}
	public LocalLevelType getLocalLevelType() {
		return localLevelType;
	}
	public void setLocalLevelType(LocalLevelType localLevelType) {
		this.localLevelType = localLevelType;
	}

}
