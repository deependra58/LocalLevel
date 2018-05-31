package com.softtech.localLevel.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <<This is the entiry for RuralMunicipality ward>>
 * 
 * @author Deependra
 * @since 9 May 2018
 * @version 1.0.1
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "ruralMunicipalWard")
public class RuralMunicipalWard extends AbstractWard {

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ruralMunicipality_id")
	private RuralMunicipality ruralMunicipality;

	private Long districtId;

	public Long getDistrictId() {
		return districtId;
	}

	public RuralMunicipality getRuralMunicipality() {
		return ruralMunicipality;
	}

	public void setRuralMunicipality(RuralMunicipality ruralMunicipality) {
		this.ruralMunicipality = ruralMunicipality;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

}
