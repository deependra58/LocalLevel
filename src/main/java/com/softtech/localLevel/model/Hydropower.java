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
public class Hydropower implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String hydropower;
	private String capacity;
	private String hydropowerImage;
	private String description;
	private String address;

	private String hydroStatus;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "district_id")
	private District district;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state_id")
	private State state;

	@Enumerated(EnumType.STRING)
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHydropower() {
		return hydropower;
	}

	public void setHydropower(String hydropower) {
		this.hydropower = hydropower;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getHydropowerImage() {
		return hydropowerImage;
	}

	public String getHydroStatus() {
		return hydroStatus;
	}

	public void setHydroStatus(String hydroStatus) {
		this.hydroStatus = hydroStatus;
	}

	public void setHydropowerImage(String hydropowerImage) {
		this.hydropowerImage = hydropowerImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
