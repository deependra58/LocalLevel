package com.softtech.localLevel.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class District {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Long id;
	private String district;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JsonBackReference
	@JoinColumn(name="state_id")
	private State state;
	
	@OneToMany(mappedBy="district",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JsonManagedReference
	private List<NewVdc> vdc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public State getState() {
		return state;
	}

	public List<NewVdc> getVdc() {
		return vdc;
	}

	public void setVdc(List<NewVdc> vdc) {
		this.vdc = vdc;
	}

	public void setState(State state) {
		this.state = state;
	}

	public District(Long id, String district, State state) {
		super();
		this.id = id;
		this.district = district;
		this.state = state;
	}

	public District() {
		super();
	}
	
	

	
	
	

}
