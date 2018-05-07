package com.softtech.localLevel.model;

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
public class ProtectedAreas {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String protectedAreas;
	private String description;
	private String Area;
	private String protectedAreasImage;
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

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProtectedAreas() {
		return protectedAreas;
	}

	public void setProtectedAreas(String protectedAreas) {
		this.protectedAreas = protectedAreas;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProtectedAreasImage() {
		return protectedAreasImage;
	}

	public void setProtectedAreasImage(String protectedAreasImage) {
		this.protectedAreasImage = protectedAreasImage;
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
