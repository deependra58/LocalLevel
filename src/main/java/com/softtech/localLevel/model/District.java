package com.softtech.localLevel.model;

import java.io.Serializable;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name="district")
public class District implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	private String district;
	private String area;
	private String population;
	private String headquater;
	private String districtPicture;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="state_id")
	@JsonBackReference
	private State state;
	
	public String getDistrictPicture() {
		return districtPicture;
	}

	public void setDistrictPicture(String districtPicture) {
		this.districtPicture = districtPicture;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getHeadquater() {
		return headquater;
	}

	public void setHeadquater(String headquater) {
		this.headquater = headquater;
	}



	public Long getId() {
		return id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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

	

	public District() {
		super();
	}

	public District(Long id) {
		this.id=id;
	}
	
	/*@OneToMany(mappedBy="district",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JsonManagedReference
	private List<NewVdc> vdc;
*/
	
	

	
	
	

}
