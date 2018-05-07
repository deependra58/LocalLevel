package com.softtech.localLevel.response;

import java.util.List;

public class InfrastructureDtoList {
	
	List<AirportsResponseDto> airports;
	List<HydropowerResponseDto> hydropower;
	List<HospitalResponseDto> hospital;
	List<IndustriesResponseDto> industry;
	public List<AirportsResponseDto> getAirports() {
		return airports;
	}
	public void setAirports(List<AirportsResponseDto> airports) {
		this.airports = airports;
	}
	public List<HydropowerResponseDto> getHydropower() {
		return hydropower;
	}
	public void setHydropower(List<HydropowerResponseDto> hydropower) {
		this.hydropower = hydropower;
	}
	public List<HospitalResponseDto> getHospital() {
		return hospital;
	}
	public void setHospital(List<HospitalResponseDto> hospital) {
		this.hospital = hospital;
	}
	public List<IndustriesResponseDto> getIndustry() {
		return industry;
	}
	public void setIndustry(List<IndustriesResponseDto> industry) {
		this.industry = industry;
	}
	public InfrastructureDtoList(List<AirportsResponseDto> airports, List<HydropowerResponseDto> hydropower,
			List<HospitalResponseDto> hospital, List<IndustriesResponseDto> industry) {
		super();
		this.airports = airports;
		this.hydropower = hydropower;
		this.hospital = hospital;
		this.industry = industry;
	}
	
	
}