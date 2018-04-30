package com.softtech.localLevel.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softtech.localLevel.util.LoginStatus;
import com.softtech.localLevel.util.UserRole;

@SuppressWarnings("serial")
@Entity
public class Login extends AbstractEntity {

	@Temporal(TemporalType.TIMESTAMP)
	private Date LastLogin;

	@Enumerated(EnumType.STRING)
	private LoginStatus loginStatus;
	@NotNull(message = "username cannot be null")
	private String username;
	@JsonIgnore
	private String password;
	private String deviceId;
	
	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	public Date getLastLogin() {
		return LastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		LastLogin = lastLogin;
	}

	public LoginStatus getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	
}
