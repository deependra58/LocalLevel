package com.softech.localLevel.exception;

import org.hibernate.service.spi.ServiceException;

public class RequiredMoreException extends ServiceException {
	
	public RequiredMoreException(String string) {
		super(string);
	}
	
}
