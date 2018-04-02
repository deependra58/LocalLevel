package com.softtech.localLevel.response;

import com.softtech.localLevel.util.LocalLevelType;

public class MunicipalityResponseDto {

	private String municipal;
	private LocalLevelType localLevelType;
	

	public LocalLevelType getLocalLevelType() {
		return localLevelType;
	}

	public void setLocalLevelType(LocalLevelType localLevelType) {
		this.localLevelType = localLevelType;
	}

	public String getMunicipal() {
		return municipal;
	}

	public void setMunicipal(String municipal) {
		this.municipal = municipal;
	}
	
}
