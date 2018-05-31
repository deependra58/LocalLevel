package com.softtech.localLevel.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.softtech.localLevel.util.Status;
/**
 * <<This is the entity for Citizen Charter>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 1 March 2018
*/

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
    private Long districtId;
    private Long municipalityId;
    private Long metropolitanId;
    private Long ruralMunicipalityId;
    private Long subMetropolitanId;
    @Enumerated(EnumType.STRING)
    private Status status;
    
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
	public ArrayList<String> getServiceRequirement() {
		return serviceRequirement;
	}
	public void setServiceRequirement(ArrayList<String> serviceRequirement) {
		this.serviceRequirement = serviceRequirement;
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
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getMunicipalityId() {
		return municipalityId;
	}
	public void setMunicipalityId(Long municipalityId) {
		this.municipalityId = municipalityId;
	}
	public Long getMetropolitanId() {
		return metropolitanId;
	}
	public void setMetropolitanId(Long metropolitanId) {
		this.metropolitanId = metropolitanId;
	}
	public Long getRuralMunicipalityId() {
		return ruralMunicipalityId;
	}
	public void setRuralMunicipalityId(Long ruralMunicipalityId) {
		this.ruralMunicipalityId = ruralMunicipalityId;
	}
	public Long getSubMetropolitanId() {
		return subMetropolitanId;
	}
	public void setSubMetropolitanId(Long subMetropolitanId) {
		this.subMetropolitanId = subMetropolitanId;
	}
    

}
 