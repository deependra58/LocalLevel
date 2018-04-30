package com.softtech.localLevel.response;

import java.util.List;



public class UrgentService {
	List<BloodBankResponse> bloodbank;
	List<PoliceStationResponse> policeStation;
	List<AtmResponse> atm;
	List<HospitalResponseDto> hospital;

	

	public UrgentService(List<BloodBankResponse> bloodBankList, List<PoliceStationResponse> policeStationList, List<AtmResponse> atmResponseList,
			List<HospitalResponseDto> hospitalResponseList) {
		super();
		this.bloodbank = bloodBankList;
		this.atm = atmResponseList;
		this.policeStation = policeStationList;
		this.hospital = hospitalResponseList;
	}



	public List<BloodBankResponse> getBloodbank() {
		return bloodbank;
	}



	public void setBloodbank(List<BloodBankResponse> bloodbank) {
		this.bloodbank = bloodbank;
	}



	public List<PoliceStationResponse> getPoliceStation() {
		return policeStation;
	}



	public void setPoliceStation(List<PoliceStationResponse> policeStation) {
		this.policeStation = policeStation;
	}



	public List<AtmResponse> getAtm() {
		return atm;
	}



	public void setAtm(List<AtmResponse> atm) {
		this.atm = atm;
	}



	public List<HospitalResponseDto> getHospital() {
		return hospital;
	}



	public void setHospital(List<HospitalResponseDto> hospital) {
		this.hospital = hospital;
	}
	
	

	

}
