package com.softtech.localLevel.dto;

import java.io.Serializable;
import java.util.List;

import com.softtech.localLevel.response.OldVdcResponseDto;

public class VdcDetailDto implements Serializable {

	private String newVdc;
	private String head;
	private String subHead;
	private String population;
	private String area;
	private String email;
	private String headPhoneNumber;
	public String getHeadPhoneNumber() {
		return headPhoneNumber;
	}

	public void setHeadPhoneNumber(String headPhoneNumber) {
		this.headPhoneNumber = headPhoneNumber;
	}

	private List<OldVdcResponseDto> oldvdc;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<OldVdcResponseDto> getOldvdc() {
		return oldvdc;
	}

	public void setOldvdc(List<OldVdcResponseDto> oldvdc) {
		this.oldvdc = oldvdc;
	}

	public String getNewVdc() {
		return newVdc;
	}

	public void setNewVdc(String newVdc) {
		this.newVdc = newVdc;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	

	public String getSubHead() {
		return subHead;
	}

	public void setSubHead(String subHead) {
		this.subHead = subHead;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	

	

}
