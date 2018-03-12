package com.softtech.localLevel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class OldVdc implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String oldVdc;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonBackReference
	@JoinColumn(name = "newVdc_id")
	private NewVdc newVdc;
	
	@OneToMany(mappedBy="oldVdc", fetch=FetchType.LAZY)
	List<Ward> wards;

	public List<Ward> getWards() {
		return wards;
	}

	public void setWards(List<Ward> wards) {
		this.wards = wards;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOldVdc() {
		return oldVdc;
	}

	public void setOldVdc(String oldVdc) {
		this.oldVdc = oldVdc;
	}

	public NewVdc getNewVdc() {
		return newVdc;
	}

	public void setNewVdc(NewVdc newVdc) {
		this.newVdc = newVdc;
	}

	public OldVdc(Long id, String oldVdc, NewVdc newVdc) {
		super();
		this.id = id;
		this.oldVdc = oldVdc;
		this.newVdc = newVdc;
	}

	public OldVdc() {
		super();
	}

	public OldVdc(Long id) {
		this.id=id;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OldVdc [id=" + id + ", oldVdc=" + oldVdc + ", newVdc=" + newVdc + "]";
	}

}
