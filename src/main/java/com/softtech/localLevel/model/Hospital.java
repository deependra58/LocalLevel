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

import org.hibernate.jpa.criteria.expression.function.SubstringFunction;

import com.softtech.localLevel.util.Status;
/**
 * <<This is the entity for Hospital>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 1 March 2018
*/
@SuppressWarnings("serial")
@Entity
public class Hospital  implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String hospital;
	private String contactNumber;
	private String address;
	private String description;

	private String hospitalImage;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="district_id")
	private District district;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="state_id")
	private State state;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	
	public String getDescription() {
		return description;
	}
	

	public String getHospitalImage() {
		return hospitalImage;
	}


	public void setHospitalImage(String hospitalImage) {
		this.hospitalImage = hospitalImage;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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
