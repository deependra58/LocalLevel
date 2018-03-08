package com.softtech.localLevel.model;

import java.io.Serializable;
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
public class NewVdc implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String newVdc;
	
	private Long population;
	private Long area;
	private String head;
	private String subHead;
	
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getSubHead() {
		return subHead;
	}
	public void setSubHead(String subHead) {
		this.subHead = subHead;
	}
	public Long getPopulation() {
		return population;
	}
	public void setPopulation(Long population) {
		this.population = population;
	}
	public Long getArea() {
		return area;
	}
	public void setArea(Long area) {
		this.area = area;
	}
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JsonBackReference
	@JoinColumn(name="district_id")
	private District district;
	
	@OneToMany(mappedBy="newVdc",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JsonManagedReference
	private List<OldVdc> OldVdc;
	
	@OneToMany(mappedBy="newVdc",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JsonManagedReference
	private List<NewWard> newWard;
	
	public NewVdc(Long id, String newVdc, District district, List<com.softtech.localLevel.model.OldVdc> oldVdc,
			List<NewWard> newWard) {
		super();
		this.id = id;
		this.newVdc = newVdc;
		this.district = district;
		OldVdc = oldVdc;
		this.newWard = newWard;
	}
	public List<NewWard> getNewWard() {
		return newWard;
	}
	public void setNewWard(List<NewWard> newWard) {
		this.newWard = newWard;
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public String getNewVdc() {
		return newVdc;
	}
	public void setNewVdc(String newVdc) {
		this.newVdc = newVdc;
	}
	
	public List<OldVdc> getOldVdc() {
		return OldVdc;
	}
	public void setOldVdc(List<OldVdc> oldVdc) {
		OldVdc = oldVdc;
	}
	public NewVdc() {
		super();
	}
	public NewVdc(Long id) {
		this.id=id;
	}
	public void setOldVdc(String string4) {
		// TODO Auto-generated method stub
		
	}
	public void setNewWard(long parseLong) {
		// TODO Auto-generated method stub
		
	}
	
}
