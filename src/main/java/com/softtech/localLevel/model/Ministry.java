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

import com.softtech.localLevel.util.GovType;
import com.softtech.localLevel.util.Status;
/**
 * <<This is the entity for Ministry>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 March 2018
*/
@SuppressWarnings("serial")
@Entity
public class Ministry implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String ministryName;
	private String ministerName;
	private String contactNumber;
	private String ministerImage;
	private String ministerEmail;
	private String stateMinister;
	private String party;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="state_id")
	private State state;

	@Enumerated(EnumType.STRING)
	private GovType govType;

	@Enumerated(EnumType.STRING)
	private Status status;

	
	public String getStateMinister() {
		return stateMinister;
	}

	public void setStateMinister(String stateMinister) {
		this.stateMinister = stateMinister;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getMinistryName() {
		return ministryName;
	}

	public void setMinistryName(String ministryName) {
		this.ministryName = ministryName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GovType getGovType() {
		return govType;
	}

	public void setGovType(GovType govType) {
		this.govType = govType;
	}

	public String getMinisterName() {
		return ministerName;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setMinisterName(String ministerName) {
		this.ministerName = ministerName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getMinisterImage() {
		return ministerImage;
	}

	public void setMinisterImage(String ministerImage) {
		this.ministerImage = ministerImage;
	}

	public String getMinisterEmail() {
		return ministerEmail;
	}

	public void setMinisterEmail(String ministerEmail) {
		this.ministerEmail = ministerEmail;
	}

	public Ministry() {
		super();
	}

	@Override
	public String toString() {
		return "Ministry [id=" + id + ", ministryName=" + ministryName + ", ministerName=" + ministerName
				+ ", contactNumber=" + contactNumber + ", ministerImage=" + ministerImage + ", ministerEmail="
				+ ministerEmail + ", govType=" + govType + ", status=" + status + "]";
	}

}
