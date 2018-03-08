package com.softtech.localLevel.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StateCreationRequest implements Serializable {
	
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
