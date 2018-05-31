package com.softtech.localLevel.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class CitizenCharterResponse {
	
	private Long id;
	ArrayList<String> serviceRequirement=new ArrayList<String>();
	private String serviceType;
    private String price;
    private String time;
    private String responsiblePerson;
    private String complainTo;
    private String remarks;
    private String district;
    private String municipality;
    private String ruralMunicipality;
    private String metropolitan;
    private String subMetropolitan;
    
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getRuralMunicipality() {
		return ruralMunicipality;
	}
	public void setRuralMunicipality(String ruralMunicipality) {
		this.ruralMunicipality = ruralMunicipality;
	}
	public String getMetropolitan() {
		return metropolitan;
	}
	public void setMetropolitan(String metropolitan) {
		this.metropolitan = metropolitan;
	}
	public String getSubMetropolitan() {
		return subMetropolitan;
	}
	public void setSubMetropolitan(String subMetropolitan) {
		this.subMetropolitan = subMetropolitan;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
    

}
