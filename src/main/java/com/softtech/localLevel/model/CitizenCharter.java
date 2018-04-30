package com.softtech.localLevel.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@SuppressWarnings("serial")
@Entity
public class CitizenCharter implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	ArrayList<String> serviceRequirement=new ArrayList<String>();
	private String serviceType;
    private String price;
    private String time;
    private String responsiblePerson;
    private String complainTo;
    private String remarks;
    
	public ArrayList<String> getServiceRequirement() {
		return serviceRequirement;
	}
	public void setServiceRequirement(ArrayList<String> serviceRequirement) {
		this.serviceRequirement = serviceRequirement;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getResponsiblePerson() {
		return responsiblePerson;
	}
	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}
	public String getComplainTo() {
		return complainTo;
	}
	public void setComplainTo(String complainTo) {
		this.complainTo = complainTo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
    
	
	

}
 