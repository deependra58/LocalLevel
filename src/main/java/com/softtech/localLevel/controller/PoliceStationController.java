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

import com.softech.localLevel.request.PoliceStationCreationRequest;
import com.softtech.localLevel.response.PoliceStationResponse;
import com.softtech.localLevel.service.PoliceStationService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/policeStation")
public class PoliceStationController {
	
	@Autowired
	private PoliceStationService policeStationService;
	
	@ApiOperation(value="Post PoliceStation",notes="Api to post police Station")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> postPoliceStation(@RequestBody PoliceStationCreationRequest policeStationCreationRequest){
		
		policeStationService.postPoliceStation(policeStationCreationRequest);
		return new ResponseEntity<Object>("Posted Successfully",HttpStatus.CREATED);
		
	}
	
	@ApiOperation(value="Get All Police Station ",notes="Api to get all police station by state name")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getPoliceStation(@RequestParam String state){
		
		List<PoliceStationResponse> policeStatResponselist=policeStationService.listAllPoliceStation(state);
		return new ResponseEntity<Object>(policeStatResponselist,HttpStatus.OK);
		
		
	}

}
