package com.softtech.localLevel.response;



public class HospitalResponseDto {
	
	private String hospital;
	private String hospitalContactNumber;
	private String district;
	private String description;
	private String addresString;
	private String hospitalImage;
	
	public String getHospitalImage() {
		return hospitalImage;
	}
	public void setHospitalImage(String hospitalImage) {
		this.hospitalImage = hospitalImage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddresString() {
		return addresString;
	}
	public void setAddresString(String addresString) {
		this.addresString = addresString;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getHospitalContactNumber() {
		return hospitalContactNumber;
	}
	public void setHospitalContactNumber(String hospitalContactNumber) {
		this.hospitalContactNumber = hospitalContactNumber;
	}
	

}
