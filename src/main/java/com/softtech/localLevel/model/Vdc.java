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

import com.softtech.localLevel.util.Status;
/**
 * <<This is the entity for Vdc>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 6 March 2018
*/
@SuppressWarnings("serial")
@Entity
public class Vdc implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String vdc;
	private Long ruralMunicipalityId;
	private Long municipalityId;
	private Long subMetropolitanId;
	private Long metropolitanId;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "district_id")
	private District district;
	//
	// @ManyToOne(cascade = { CascadeType.ALL })
	// @JoinColumn(name = "municipality_id")
	// private Municipality municipality;
	//
	// @ManyToOne(cascade = { CascadeType.ALL })
	// @JoinColumn(name = "subMetropolitan_id")
	// private SubMetropolitan subMetropolitan;
	//
	// @ManyToOne(cascade = { CascadeType.ALL })
	// @JoinColumn(name = "metropolitan_id")
	// private Metropolitan metropolitan;

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

	// public RuralMunicipality getRuralMunicipality() {
	// return ruralMunicipality;
	// }
	//
	// public void setRuralMunicipality(RuralMunicipality ruralMunicipality) {
	// this.ruralMunicipality = ruralMunicipality;
	// }
	//
	// public District getDistrict() {
	// return district;
	// }
	//
	// public void setDistrict(District district) {
	// this.district = district;
	// }
	//
	// public Municipality getMunicipality() {
	// return municipality;
	// }
	//
	// public void setMunicipality(Municipality municipality) {
	// this.municipality = municipality;
	// }
	//
	// public SubMetropolitan getSubMetropolitan() {
	// return subMetropolitan;
	// }
	//
	// public void setSubMetropolitan(SubMetropolitan subMetropolitan) {
	// this.subMetropolitan = subMetropolitan;
	// }
	//
	// public Metropolitan getMetropolitan() {
	// return metropolitan;
	// }
	//
	// public void setMetropolitan(Metropolitan metropolitan) {
	// this.metropolitan = metropolitan;
	// }

	public List<Ward> getWard() {
		return ward;
	}

	public Long getRuralMunicipalityId() {
		return ruralMunicipalityId;
	}

	public void setRuralMunicipalityId(Long ruralMunicipalityId) {
		this.ruralMunicipalityId = ruralMunicipalityId;
	}

	public Long getMunicipalityId() {
		return municipalityId;
	}

	public void setMunicipalityId(Long municipalityId) {
		this.municipalityId = municipalityId;
	}

	public Long getSubMetropolitanId() {
		return subMetropolitanId;
	}

	public void setSubMetropolitanId(Long subMetropolitanId) {
		this.subMetropolitanId = subMetropolitanId;
	}

	public Long getMetropolitanId() {
		return metropolitanId;
	}

	public void setMetropolitanId(Long metropolitanId) {
		this.metropolitanId = metropolitanId;
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

	// @Override
	// public String toString() {
	// return "Vdc [id=" + id + ", vdc=" + vdc + ", ruralMunicipality=" +
	// ruralMunicipality + ", district=" + district
	// + ", municipality=" + municipality + ", subMetropolitan=" + subMetropolitan +
	// ", metropolitan="
	// + metropolitan + ", ward=" + ward + ", status=" + status + "]";
	// }
	//

}
