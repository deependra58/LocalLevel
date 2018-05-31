package com.softtech.localLevel.model;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.softtech.localLevel.util.Status;

/**
 * <<This is an abstract ward that is extended by
 * metroward,submetroward,municipalward and ruralMunicipalward>>
 * 
 * @Author:Deependra
 * @Since: 9 May 2018
 * @Version:1.0.1
 */

@SuppressWarnings("serial")
@MappedSuperclass
public class AbstractWard implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	protected Long newWardId;
	protected String newWardName;
	protected Long oldWardId;
	protected String oldWardName;
	protected String oldVdcName;

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

	public String getOldVdcName() {
		return oldVdcName;
	}

	public void setOldVdcName(String oldVdcName) {
		this.oldVdcName = oldVdcName;
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

	public String getNewWardName() {
		return newWardName;
	}

	public void setNewWardName(String newWardName) {
		this.newWardName = newWardName;
	}

	public Long getOldWardId() {
		return oldWardId;
	}

	public void setOldWardId(Long oldWardId) {
		this.oldWardId = oldWardId;
	}

	public String getOldWardName() {
		return oldWardName;
	}

	public void setOldWardName(String oldWardName) {
		this.oldWardName = oldWardName;
	}

}
