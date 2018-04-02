package com.softtech.localLevel.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vdc {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String vdc;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "ruralMunicipality_id")
	private RuralMunicipality ruralMunicipality;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "district_id")
	private District district;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "municipality_id")
	private Municipality municipality;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="subMetropolitan_id")
	private SubMetropolitan subMetropolitan;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="metropolitan_id")
	private Metropolitan metropolitan;
	

	public Metropolitan getMetropolitan() {
		return metropolitan;
	}

	public void setMetropolitan(Metropolitan metropolitan) {
		this.metropolitan = metropolitan;
	}

	public SubMetropolitan getSubMetropolitan() {
		return subMetropolitan;
	}

	public void setSubMetropolitan(SubMetropolitan subMetropolitan) {
		this.subMetropolitan = subMetropolitan;
	}

	public RuralMunicipality getRuralMunicipality() {
		return ruralMunicipality;
	}

	public void setRuralMunicipality(RuralMunicipality ruralMunicipality) {
		this.ruralMunicipality = ruralMunicipality;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Municipality getMunicipality() {
		return municipality;
	}

	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVdc() {
		return vdc;
	}

	public void setVdc(String vdc) {
		this.vdc = vdc;
	}

}
