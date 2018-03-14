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
package com.softtech.localLevel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * <<Description Here>>
 * @author Lothbroke
 * @version 
 * @since , Feb 27, 2018
 */

@Entity
@Table(name="state")
public class State  implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String state;
	
	private String mayor;
	private String deputMayor;
	private String website;
	private String area;
	private String capital;
	private String density;
	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public void setDeputMayor(String deputMayor) {
		this.deputMayor = deputMayor;
	}

	private String population;
	
	
	public String getMayor() {
		return mayor;
	}

	public void setMayor(String mayor) {
		this.mayor = mayor;
	}

	public String getDeputMayor() {
		return deputMayor;
	}

	public void setDeput_Mayor(String deputMayor) {
		this.deputMayor = deputMayor;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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

	@OneToMany(mappedBy="state",fetch=FetchType.LAZY)
	private List<District> districts;

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

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	public State(Long id, String state, List<District> districts) {
		super();
		this.id = id;
		this.state = state;
		this.districts = districts;
	}

	public State(Long id) {
		super();
		this.id = id;
	}

	public State(String state) {
		super();
		this.state = state;
	}

	public State() {
		super();
	}
	
	
	
}
