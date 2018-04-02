package com.softtech.localLevel.response;

import com.softtech.localLevel.util.LocalLevelType;

public class RuralMunicipalResponseDto {

	private String ruralMunicipal;
	private LocalLevelType localLevelType;
	

	public LocalLevelType getLocalLevelType() {
		return localLevelType;
	}

	public void setLocalLevelType(LocalLevelType localLevelType) {
		this.localLevelType = localLevelType;
	}

	public String getRuralMunicipal() {
		return ruralMunicipal;
	}

	public void setRuralMunicipal(String ruralMunicipal) {
		this.ruralMunicipal = ruralMunicipal;
	}
	
}
