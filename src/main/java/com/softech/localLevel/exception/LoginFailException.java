package com.softech.localLevel.exception;

import org.hibernate.service.spi.ServiceException;

@SuppressWarnings("serial")
public class LoginFailException extends ServiceException {
	
	public LoginFailException(String message) {
		super (message);
	}

}
