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
public class Waterfall implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String waterfall;
	private String waterfallImage;
	private String description;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="district")
	private District district;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="state")
	private State state;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWaterfall() {
		return waterfall;
	}

	public void setWaterfall(String waterfall) {
		this.waterfall = waterfall;
	}

	public String getWaterfallImage() {
		return waterfallImage;
	}

	public void setWaterfallImage(String waterfallImage) {
		this.waterfallImage = waterfallImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
