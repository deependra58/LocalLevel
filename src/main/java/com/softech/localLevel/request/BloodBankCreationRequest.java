package com.softech.localLevel.request;

import com.softtech.localLevel.util.Status;

public class BloodBankCreationRequest {
	
	private String bloodBankName;
	private String localAddress;
	private String contactNo;
	//private String state;
	private String district;
	private Status status;
	public String getBloodBankName() {
		return bloodBankName;
	}
	public void setBloodBankName(String bloodBankName) {
		this.bloodBankName = bloodBankName;
	}
	public String getLocalAddress() {
		return localAddress;
	}
	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
