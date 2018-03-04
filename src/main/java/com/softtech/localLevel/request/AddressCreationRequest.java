/*************************************************************************
 * 
 *  All Rights Reserved.
 * 
 * NOTICE:  All information contained here in is, and remains
 * the property of Texas Imaginology and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * here in are proprietary to Texas Imaginology. Dissemination of this
 * information or reproduction of this material is strictly forbidden unless
 * prior written permission is obtained from Texas Imaginology.
 * 
 */
package com.softtech.localLevel.request;

import java.io.Serializable;

/**
 * <<Description Here>>
 * @author Lothbroke
 * @version 
 * @since , Feb 27, 2018
 */
public class AddressCreationRequest implements Serializable {
	
	private Long id;
	private String state;
	private String district;
	private String vdc;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getVdc() {
		return vdc;
	}
	public void setVdc(String vdc) {
		this.vdc = vdc;
	}
	
	

}
