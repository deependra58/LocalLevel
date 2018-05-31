package com.softtech.localLevel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softech.localLevel.request.AtmCreationRequest;
import com.softtech.localLevel.response.AtmResponse;
import com.softtech.localLevel.service.AtmService;

import io.swagger.annotations.ApiOperation;
/**
 * <<This is the controller for Atm>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 March 2018
*/
@RestController
@RequestMapping("rest/Atms")
public class AtmController {
	
	@Autowired
	private AtmService atmService;
	
	/*Api to post atm manually*/
	@ApiOperation(value="Post Atm",notes="Api to post ATM")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> postAtm(@RequestBody AtmCreationRequest atmCreationRequest){
		
		atmService.postAtm(atmCreationRequest);
		return new ResponseEntity<Object>("Posted Successfully",HttpStatus.CREATED);
		
	}
	
	/*Api to get all Atm by state*/
	@ApiOperation(value="Get All Atm ",notes="Api to get all atm by state name")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getAtm(@RequestParam String state){
		
		List<AtmResponse> atmResponseList=atmService.getAllAtm(state);
		return new ResponseEntity<Object>(atmResponseList,HttpStatus.OK);
		
		
	}

}
