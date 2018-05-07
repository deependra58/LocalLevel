package com.softtech.localLevel.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.softtech.localLevel.util.Status;
/**
 * <<This is the entity for Area>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 March 2018
*/
@SuppressWarnings("serial")
@Entity
public class Rivers implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String river;
	private String description;
	private String riverImageString;

	@ManyToOne(cascade = CascadeType.ALL)
	private State state;

	@Enumerated(EnumType.STRING)
	private Status status;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRiver() {
		return river;
	}

	public void setRiver(String river) {
		this.river = river;
	}

	public String getRiverImageString() {
		return riverImageString;
	}

	public void setRiverImageString(String riverImageString) {
		this.riverImageString = riverImageString;
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
