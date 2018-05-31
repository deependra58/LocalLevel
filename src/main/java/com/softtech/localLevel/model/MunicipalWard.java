package com.softtech.localLevel.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*<<This is the entity for municipality ward>>
 * @Author Deependra
 * @Since 10 ,May 2018
 * @Version 1.0.1*/
@SuppressWarnings("serial")
@Entity
public class MunicipalWard  extends AbstractWard{
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="municipality_id")
	private Municipality municipality;
	
	private Long districtId;

	public Municipality getMunicipality() {
		return municipality;
	}

	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	

}
