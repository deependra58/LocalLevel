package com.softtech.localLevel.response;

import java.util.List;

public class LocalLevelResponseDto {
	
	private List<RuralMunicipalResponseDto> ruralMunicipal;
	private List<MunicipalityResponseDto> municipal;
	private List<SubMetropolitanResponseDto> subMetropolitan;
	private List<MetropolitanResponseDto> metropolitan;
	public List<SubMetropolitanResponseDto> getSubMetropolitan() {
		return subMetropolitan;
	}
	public void setSubMetropolitan(List<SubMetropolitanResponseDto> subMetropolitan) {
		this.subMetropolitan = subMetropolitan;
	}
	public List<MetropolitanResponseDto> getMetropolitan() {
		return metropolitan;
	}
	public void setMetropolitan(List<MetropolitanResponseDto> metropolitan) {
		this.metropolitan = metropolitan;
	}
	public List<RuralMunicipalResponseDto> getRuralMunicipal() {
		return ruralMunicipal;
	}
	public void setRuralMunicipal(List<RuralMunicipalResponseDto> ruralMunicipal) {
		this.ruralMunicipal = ruralMunicipal;
	}
	public List<MunicipalityResponseDto> getMunicipal() {
		return municipal;
	}
	public void setMunicipal(List<MunicipalityResponseDto> municipal) {
		this.municipal = municipal;
	}
	public LocalLevelResponseDto(List<RuralMunicipalResponseDto> ruralMunicipal,
			List<MunicipalityResponseDto> municipal, List<SubMetropolitanResponseDto> subMetropolitan,
			List<MetropolitanResponseDto> metropolitan) {
		super();
		this.ruralMunicipal = ruralMunicipal;
		this.municipal = municipal;
		this.subMetropolitan = subMetropolitan;
		this.metropolitan = metropolitan;
	}
	
	
	
	
	

}
