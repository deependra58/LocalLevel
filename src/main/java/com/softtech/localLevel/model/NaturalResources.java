package com.softtech.localLevel.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.softtech.localLevel.util.Status;

@Entity
public class NaturalResources implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String mountain;
	private String river;
	private String lake;
	private String waterfalls;
	private String protectedAreas;
	private String mountainHeight;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state_id")
	private State state;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "district_id")
	private District district;

	@Enumerated(EnumType.STRING)
	private Status status;

	
	public String getMountainHeight() {
		return mountainHeight;
	}

	public void setMountainHeight(String mountainHeight) {
		this.mountainHeight = mountainHeight;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMountain() {
		return mountain;
	}

	public void setMountain(String mountain) {
		this.mountain = mountain;
	}

	public String getRiver() {
		return river;
	}

	public void setRiver(String river) {
		this.river = river;
	}

	public String getLake() {
		return lake;
	}

	public void setLake(String lake) {
		this.lake = lake;
	}

	public String getWaterfalls() {
		return waterfalls;
	}

	public void setWaterfalls(String waterfalls) {
		this.waterfalls = waterfalls;
	}

	public String getProtectedAreas() {
		return protectedAreas;
	}

	public void setProtectedAreas(String protectedAreas) {
		this.protectedAreas = protectedAreas;
	}

	public NaturalResources() {
		super();
		// TODO Auto-generated constructor stub
	}

}
