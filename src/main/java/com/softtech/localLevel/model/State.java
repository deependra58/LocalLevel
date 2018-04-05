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
 * @since , Feb 27, 2018
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
	private List<NaturalResources> naturalResources;
	
	@OneToMany(mappedBy="state",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Infrastructure> infracture;
	
	@OneToMany(mappedBy="state",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Demographic> demographic;

	public List<Infrastructure> getInfracture() {
		return infracture;
	}

	public void setInfracture(List<Infrastructure> infracture) {
		this.infracture = infracture;
	}

	public List<Demographic> getDemographic() {
		return demographic;
	}

	public void setDemographic(List<Demographic> demographic) {
		this.demographic = demographic;
	}

	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ministry> ministry;

	@Enumerated(EnumType.STRING)
	private LocalLevelType localLevelType;

	public List<NaturalResources> getNaturalResources() {
		return naturalResources;
	}

	public void setNaturalResources(List<NaturalResources> naturalResources) {
		this.naturalResources = naturalResources;
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
