package com.softtech.localLevel.model;

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

import com.softtech.localLevel.util.Status;

@Entity
public class Vdc {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String vdc;
	private Long ruralMuniciaplity_id;
	private Long municipality_id;
	private Long submetropolitan_id;
	private Long metropolitan_id;

//	@ManyToOne(cascade = { CascadeType.ALL })
//	@JoinColumn(name = "ruralMunicipality_id")
//	private RuralMunicipality ruralMunicipality;
//
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "district_id")
	private District district;
//
//	@ManyToOne(cascade = { CascadeType.ALL })
//	@JoinColumn(name = "municipality_id")
//	private Municipality municipality;
//
//	@ManyToOne(cascade = { CascadeType.ALL })
//	@JoinColumn(name = "subMetropolitan_id")
//	private SubMetropolitan subMetropolitan;
//
//	@ManyToOne(cascade = { CascadeType.ALL })
//	@JoinColumn(name = "metropolitan_id")
//	private Metropolitan metropolitan;

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@OneToMany(mappedBy = "vdc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ward> ward;

	@Enumerated(EnumType.STRING)
	private Status status;

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
	

//	public RuralMunicipality getRuralMunicipality() {
//		return ruralMunicipality;
//	}
//
//	public void setRuralMunicipality(RuralMunicipality ruralMunicipality) {
//		this.ruralMunicipality = ruralMunicipality;
//	}
//
//	public District getDistrict() {
//		return district;
//	}
//
//	public void setDistrict(District district) {
//		this.district = district;
//	}
//
//	public Municipality getMunicipality() {
//		return municipality;
//	}
//
//	public void setMunicipality(Municipality municipality) {
//		this.municipality = municipality;
//	}
//
//	public SubMetropolitan getSubMetropolitan() {
//		return subMetropolitan;
//	}
//
//	public void setSubMetropolitan(SubMetropolitan subMetropolitan) {
//		this.subMetropolitan = subMetropolitan;
//	}
//
//	public Metropolitan getMetropolitan() {
//		return metropolitan;
//	}
//
//	public void setMetropolitan(Metropolitan metropolitan) {
//		this.metropolitan = metropolitan;
//	}

	public Long getRuralMuniciaplity_id() {
		return ruralMuniciaplity_id;
	}

	public void setRuralMuniciaplity_id(Long ruralMuniciaplity_id) {
		this.ruralMuniciaplity_id = ruralMuniciaplity_id;
	}

	public Long getMunicipality_id() {
		return municipality_id;
	}

	public void setMunicipality_id(Long municipality_id) {
		this.municipality_id = municipality_id;
	}

	public Long getSubmetropolitan_id() {
		return submetropolitan_id;
	}

	public void setSubmetropolitan_id(Long submetropolitan_id) {
		this.submetropolitan_id = submetropolitan_id;
	}

	public Long getMetropolitan_id() {
		return metropolitan_id;
	}

	public void setMetropolitan_id(Long metropolitan_id) {
		this.metropolitan_id = metropolitan_id;
	}

	public List<Ward> getWard() {
		return ward;
	}

	public void setWard(List<Ward> ward) {
		this.ward = ward;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

//	@Override
//	public String toString() {
//		return "Vdc [id=" + id + ", vdc=" + vdc + ", ruralMunicipality=" + ruralMunicipality + ", district=" + district
//				+ ", municipality=" + municipality + ", subMetropolitan=" + subMetropolitan + ", metropolitan="
//				+ metropolitan + ", ward=" + ward + ", status=" + status + "]";
//	}
//	
	

}
