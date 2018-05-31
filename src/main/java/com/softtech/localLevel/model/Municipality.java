package com.softtech.localLevel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.softtech.localLevel.util.LocalLevelType;
import com.softtech.localLevel.util.Status;

/**
 * <<This is the entity for Municipality>>
 * 
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 March 2018
 */
@SuppressWarnings("serial")
@Entity
public class Municipality implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String municipal;
	private String population;
	private String area;
	private String density;
	private String website;
	private String mayor;
	private String mayorContact;
	private String mayorEmail;
	private String deputMayor;
	private String deputMayorContact;
	private String deputMayorEmail;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Enumerated(EnumType.STRING)
	private LocalLevelType localLevelType;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "district_id")
	private District district;

	@OneToMany(mappedBy = "municipality", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MunicipalWard> municipalWard;

	// @OneToMany(mappedBy="municipality", cascade=CascadeType.ALL,
	// fetch=FetchType.LAZY)
	// private List<Vdc> vdc;
	//
	// public List<Vdc> getVdc() {
	// return vdc;
	// }
	//
	// public void setVdc(List<Vdc> vdc) {
	// this.vdc = vdc;
	// }

	public LocalLevelType getLocalLevelType() {
		return localLevelType;
	}

	public List<MunicipalWard> getMunicipalWard() {
		return municipalWard;
	}

	public void setMunicipalWard(List<MunicipalWard> municipalWard) {
		this.municipalWard = municipalWard;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setLocalLevelType(LocalLevelType localLevelType) {
		this.localLevelType = localLevelType;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMunicipal() {
		return municipal;
	}

	public void setMunicipal(String municipal) {
		this.municipal = municipal;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
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

	public String getMayorContact() {
		return mayorContact;
	}

	public void setMayorContact(String mayorContact) {
		this.mayorContact = mayorContact;
	}

	public String getMayorEmail() {
		return mayorEmail;
	}

	public void setMayorEmail(String mayorEmail) {
		this.mayorEmail = mayorEmail;
	}

	public String getDeputMayor() {
		return deputMayor;
	}

	public void setDeputMayor(String deputMayor) {
		this.deputMayor = deputMayor;
	}

	public String getDeputMayorContact() {
		return deputMayorContact;
	}

	public void setDeputMayorContact(String deputMayorContact) {
		this.deputMayorContact = deputMayorContact;
	}

	public String getDeputMayorEmail() {
		return deputMayorEmail;
	}

	public void setDeputMayorEmail(String deputMayorEmail) {
		this.deputMayorEmail = deputMayorEmail;
	}

	public Municipality() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Municipality(Long id) {
		this.id = id;
	}

}
