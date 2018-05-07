package com.softtech.localLevel.dto;

import java.util.List;

public class StateDetailDto {
	
	private String area;
	private String population;
	private String website;
	private String governer;
	private String chiefMinister;
	private String capital;
	private String density;
	private String statePicture;
	
	private List<DistrictDto> district;

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

	public String getGoverner() {
		return governer;
	}

	public void setGoverner(String governer) {
		this.governer = governer;
	}

	public String getChiefMinister() {
		return chiefMinister;
	}

	public void setChiefMinister(String chiefMinister) {
		this.chiefMinister = chiefMinister;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public String getStatePicture() {
		return statePicture;
	}

	public void setStatePicture(String statePicture) {
		this.statePicture = statePicture;
	}

	public List<DistrictDto> getDistrict() {
		return district;
	}

	public void setDistrict(List<DistrictDto> district) {
		this.district = district;
	}
	
	

}
