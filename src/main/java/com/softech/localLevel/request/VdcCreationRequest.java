package com.softech.localLevel.request;

public class VdcCreationRequest {
	
	private String vdc;
	private Long districtId;
	private Long ruralMetropolitanId;
	private Long municipalityId;
	private Long subMetropolitanId;
	private Long metropolitan;
	public String getVdc() {
		return vdc;
	}
	public void setVdc(String vdc) {
		this.vdc = vdc;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getRuralMetropolitanId() {
		return ruralMetropolitanId;
	}
	public void setRuralMetropolitanId(Long ruralMetropolitanId) {
		this.ruralMetropolitanId = ruralMetropolitanId;
	}
	public Long getMunicipalityId() {
		return municipalityId;
	}
	public void setMunicipalityId(Long municipalityId) {
		this.municipalityId = municipalityId;
	}
	public Long getSubMetropolitanId() {
		return subMetropolitanId;
	}
	public void setSubMetropolitanId(Long subMetropolitanId) {
		this.subMetropolitanId = subMetropolitanId;
	}
	public Long getMetropolitan() {
		return metropolitan;
	}
	public void setMetropolitan(Long metropolitan) {
		this.metropolitan = metropolitan;
	}
	
	
}
