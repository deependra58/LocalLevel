package com.softtech.localLevel.dto;

import java.util.List;

import com.softtech.localLevel.model.District;

public class StateDetailDto {
	
	private String area;
	private String population;
	private String website;
	private String mayor;
	private String deputMayor;
	private String capital;
	private String density;
	private String statePicture;
	public String getStatePicture() {
		return statePicture;
	}
	public void setStatePicture(String statePicture) {
		this.statePicture = statePicture;
	}
	private List<DistrictDto> district;
	
	
	public List<DistrictDto> getDistrict() {
		return district;
	}
	public void setDistrict(List<DistrictDto> districtDtoList) {
		this.district = districtDtoList;
	}
	public String getDensity() {
		return density;
	}
	public void setDensity(String density) {
		this.density = density;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getMayor() {
		return mayor;
	}
	public void setMayor(String mayor) {
		this.mayor = mayor;
	}
	public String getDeputMayor() {
		return deputMayor;
	}
	public void setDeputMayor(String deputMayor) {
		this.deputMayor = deputMayor;
	}
	
	

}
