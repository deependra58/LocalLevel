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
import javax.persistence.Table;

import com.softtech.localLevel.util.LocalLevelType;

@Entity
@Table(name="ruralMunicipality")
public class RuralMunicipality implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String ruralMunicipal;
	private String population;
	private String area;
	private String density;
	private String website;
	private String chairmen;
	private String chairmenContact;
	private String chairmenEmail;
	private String viceChairmen;
	private String viceChairmenContact;
	private String viceChairmenEmail;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="district_id")
	private District district;
	
	@OneToMany(mappedBy="ruralMunicipality", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Vdc> vdc;
	
	
	public List<Vdc> getVdc() {
		return vdc;
	}
	public void setVdc(List<Vdc> vdc) {
		this.vdc = vdc;
	}
	@Enumerated(EnumType.STRING)
	private LocalLevelType localLevelType;
	
	
	public LocalLevelType getLocalLevelType() {
		return localLevelType;
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
	public String getRuralMunicipal() {
		return ruralMunicipal;
	}
	public void setRuralMunicipal(String ruralMunicipal) {
		this.ruralMunicipal = ruralMunicipal;
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
	public String getChairmen() {
		return chairmen;
	}
	public void setChairmen(String chairmen) {
		this.chairmen = chairmen;
	}
	public String getChairmenContact() {
		return chairmenContact;
	}
	public void setChairmenContact(String chairmenContact) {
		this.chairmenContact = chairmenContact;
	}
	public String getChairmenEmail() {
		return chairmenEmail;
	}
	public void setChairmenEmail(String chairmenEmail) {
		this.chairmenEmail = chairmenEmail;
	}
	public String getViceChairmen() {
		return viceChairmen;
	}
	public void setViceChairmen(String viceChairmen) {
		this.viceChairmen = viceChairmen;
	}
	public String getViceChairmenContact() {
		return viceChairmenContact;
	}
	public void setViceChairmenContact(String viceChairmenContact) {
		this.viceChairmenContact = viceChairmenContact;
	}
	public String getViceChairmenEmail() {
		return viceChairmenEmail;
	}
	public void setViceChairmenEmail(String viceChairmenEmail) {
		this.viceChairmenEmail = viceChairmenEmail;
	}
	public RuralMunicipality() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RuralMunicipality(Long id, String ruralMunicipal, String population, String area, String density,
			String website, String chairmen, String chairmenContact, String chairmenEmail, String viceChairmen,
			String viceChairmenContact, String viceChairmenEmail) {
		super();
		this.id = id;
		this.ruralMunicipal = ruralMunicipal;
		this.population = population;
		this.area = area;
		this.density = density;
		this.website = website;
		this.chairmen = chairmen;
		this.chairmenContact = chairmenContact;
		this.chairmenEmail = chairmenEmail;
		this.viceChairmen = viceChairmen;
		this.viceChairmenContact = viceChairmenContact;
		this.viceChairmenEmail = viceChairmenEmail;
	}
	public RuralMunicipality(Long id) {
		this.id=id;
	}
	
	
	

}
