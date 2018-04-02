package com.softtech.localLevel.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ward implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long newWardId;
	private Long oldWardId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="vdc_id")
	private Vdc vdc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNewWardId() {
		return newWardId;
	}

	public void setNewWardId(Long newWardId) {
		this.newWardId = newWardId;
	}

	public Long getOldWardId() {
		return oldWardId;
	}

	public void setOldWardId(Long oldWardId) {
		this.oldWardId = oldWardId;
	}

	public Vdc getVdc() {
		return vdc;
	}

	public void setVdc(Vdc vdc) {
		this.vdc = vdc;
	}

	
	
	

}
