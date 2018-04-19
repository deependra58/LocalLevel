package com.softtech.localLevel.dto;

public class MinistryDetailsDto {
	
	private String ministryName;
	private String ministerName;
	private String contactNumber;
	private String ministerImage;
	private String ministerEmail;
	private String party;
	private String stateMinister;
	
	
	public String getStateMinister() {
		return stateMinister;
	}
	public void setStateMinister(String stateMinister) {
		this.stateMinister = stateMinister;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getMinistryName() {
		return ministryName;
	}
	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
	}
	public String getMinisterName() {
		return ministerName;
	}
	public void setMinisterName(String ministerName) {
		this.ministerName = ministerName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getMinisterImage() {
		return ministerImage;
	}
	public void setMinisterImage(String ministerImage) {
		this.ministerImage = ministerImage;
	}
	public String getMinisterEmail() {
		return ministerEmail;
	}
	public void setMinisterEmail(String ministerEmail) {
		this.ministerEmail = ministerEmail;
	}
	@Override
	public String toString() {
		return "MinistryDetailsDto [ministryName=" + ministryName + ", ministerName=" + ministerName
				+ ", contactNumber=" + contactNumber + ", ministerImage=" + ministerImage + ", ministerEmail="
				+ ministerEmail + "]";
	}
	

}
