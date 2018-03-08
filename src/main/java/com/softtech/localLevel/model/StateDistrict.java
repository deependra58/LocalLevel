/*package com.softtech.localLevel.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stateDistrict")
public class StateDistrict implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long districtId;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public StateDistrict(Long districtId, State stateId) {
		super();
		this.districtId = districtId;
		this.state = stateId;
	}

	public State getStateId() {
		return state;
	}

	public void setStateId(State stateId) {
		this.state = stateId;
	}

	public StateDistrict() {
		super();
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	

}
*/