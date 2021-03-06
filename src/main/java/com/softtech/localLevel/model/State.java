/*************************************************************************
 * 
 *  All Rights Reserved.
 * 
 * NOTICE:  All information contained here in is, and remains
 * the property of Texas Imaginology and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here in are proprietary to Texas Imaginology. Dissemination of this
 * information or reproduction of this material is strictly forbidden unless
 * prior written permission is obtained from Texas Imaginology.
 * 
 */
package com.softtech.localLevel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.softtech.localLevel.util.LocalLevelType;

/**
 * <<Description Here>>
 * 
 * @author Lothbroke
 * @version
 * @since , march 4, 2018
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "state")
public class State implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String governer;
	private String chiefMinister;
	private String website;
	private String area;
	private String capital;
	private String density;
	private String statePicture;
	private String population;

	@Column(unique = true)
	private String state;
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @JsonManagedReference
	private List<District> districts;

	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ministry> ministry;

	@Enumerated(EnumType.STRING)
	private LocalLevelType localLevelType;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Mountains> mountains;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Lakes> lakes;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Waterfall> waterfall;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Atm> atm;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<BloodBank> bloodBank;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Airports> airports;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Hospital> hospital;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Hydropower> hydropower;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Industry> industry;

	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PoliceStation> policeStation;

	public List<Atm> getAtm() {
		return atm;
	}

	public void setAtm(List<Atm> atm) {
		this.atm = atm;
	}

	public List<PoliceStation> getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(List<PoliceStation> policeStation) {
		this.policeStation = policeStation;
	}

	public List<Hydropower> getHydropower() {
		return hydropower;
	}

	public void setHydropower(List<Hydropower> hydropower) {
		this.hydropower = hydropower;
	}

	public List<BloodBank> getBloodBank() {
		return bloodBank;
	}

	public void setBloodBank(List<BloodBank> bloodBank) {
		this.bloodBank = bloodBank;
	}

	public List<Industry> getIndustry() {
		return industry;
	}

	public void setIndustry(List<Industry> industry) {
		this.industry = industry;
	}

	public List<Hospital> getHospital() {
		return hospital;
	}

	public void setHospital(List<Hospital> hospital) {
		this.hospital = hospital;
	}

	public List<Mountains> getMountains() {
		return mountains;
	}

	public void setMountains(List<Mountains> mountains) {
		this.mountains = mountains;
	}

	public List<Lakes> getLakes() {
		return lakes;
	}

	public void setLakes(List<Lakes> lakes) {
		this.lakes = lakes;
	}

	public List<Waterfall> getWaterfall() {
		return waterfall;
	}

	public void setWaterfall(List<Waterfall> waterfall) {
		this.waterfall = waterfall;
	}

	public List<Airports> getAirports() {
		return airports;
	}

	public void setAirports(List<Airports> airports) {
		this.airports = airports;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public List<Ministry> getMinistry() {
		return ministry;
	}

	public void setMinistry(List<Ministry> ministry) {
		this.ministry = ministry;
	}

	public LocalLevelType getLocalLevelType() {
		return localLevelType;
	}

	public void setLocalLevelType(LocalLevelType localLevelType) {
		this.localLevelType = localLevelType;
	}

	public State() {
		super();
		// TODO Auto-generated constructor stub
	}

	public State(Long id) {
		this.id = id;
	}

}
