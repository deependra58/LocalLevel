package com.softtech.localLevel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

/**
 * <<This is the controller for status >>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 April 2018
*/
@RestController
@RequestMapping("/api/v1/status")
public class StatusController {

	private static final Logger LOG = LoggerFactory.getLogger(StatusController.class);

	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", required = true, dataType = "string", paramType = "header") })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> check() {
		LOG.info("Checking status.");
		return new ResponseEntity<Object>("Running...", HttpStatus.OK);
	}
}
