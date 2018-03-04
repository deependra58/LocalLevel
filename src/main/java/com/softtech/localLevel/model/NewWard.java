package com.softtech.localLevel.model;

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

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class NewWard {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	private Long NewWard;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JsonBackReference
	@JoinColumn(name="newVdc_id")
	private NewVdc newVdc;
	
	@OneToMany(mappedBy="newWard",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JsonManagedReference
	private List<OldWard> OldWard;
	
	public Long getId() {
		return id;
	}
	public NewWard(Long id, Long newWard, NewVdc newVdc, List<com.softtech.localLevel.model.OldWard> oldWard) {
		super();
		this.id = id;
		NewWard = newWard;
		this.newVdc = newVdc;
		OldWard = oldWard;
	}
	public List<OldWard> getOldWard() {
		return OldWard;
	}
	public void setOldWard(List<OldWard> oldWard) {
		OldWard = oldWard;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNewWard() {
		return NewWard;
	}
	public void setNewWard(Long newWard) {
		NewWard = newWard;
	}
	public NewVdc getNewVdc() {
		return newVdc;
	}
	public void setNewVdc(NewVdc newVdc) {
		this.newVdc = newVdc;
	}
	public NewWard(Long id, Long newWard, NewVdc newVdc) {
		super();
		this.id = id;
		NewWard = newWard;
		this.newVdc = newVdc;
	}
	public NewWard() {
		super();
	}
	
	
	

}
