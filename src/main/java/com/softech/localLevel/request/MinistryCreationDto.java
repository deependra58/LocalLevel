package com.softech.localLevel.request;

public class MinistryCreationDto {

	private String ministryName;
	private String ministerName;
	private String contactNumber;
	private String ministerImage;
	private String ministerEmail;
	private String party;
	
	
//	public Long getStateId() {
//		return stateId;
//	}
//	public void setStateId(Long stateId) {
//		this.stateId = stateId;
//	}
	
	public String getMinistryName() {
		return ministryName;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
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
	
}
