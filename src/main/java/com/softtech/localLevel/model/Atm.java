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

@SuppressWarnings("serial")
@Entity
public class Atm implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String AtmName;
	private String localAddress;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state_id")
	private State state;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "district_id")
	private District district;

	@Enumerated(EnumType.STRING)
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAtmName() {
		return AtmName;
	}

	public void setAtmName(String atmName) {
		AtmName = atmName;
	}

	public String getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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

}
