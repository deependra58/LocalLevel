package com.softtech.localLevel.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/*
 * <<This is the entity for SubMetropolitan >>
 * @Author Deependra
 * @Since 10, May 2018
 * @Version 1.0.1
 * 
 * */
@SuppressWarnings("serial")
@Entity
public class SubMetropolitanWard  extends AbstractWard{
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="metropolitan_id")
	private SubMetropolitan subMetropolitan;
	
	private Long district_id;

	public SubMetropolitan getSubMetropolitan() {
		return subMetropolitan;
	}

	public void setSubMetropolitan(SubMetropolitan subMetropolitan) {
		this.subMetropolitan = subMetropolitan;
	}

	public Long getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(Long district_id) {
		this.district_id = district_id;
	}
	

}
