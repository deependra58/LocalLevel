package com.softtech.localLevel.response;

public class AtmResponse {

	private String AtmName;
	private String localAddress;
	private String district;
	public String getAtmName() {
		return AtmName;
	}
	public void setAtmName(String atmName) {
		AtmName = atmName;
	}
	public String getLocalAddress() {
		return localAddress;
	}
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
}
