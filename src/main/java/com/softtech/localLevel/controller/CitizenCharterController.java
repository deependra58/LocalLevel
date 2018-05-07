package com.softtech.localLevel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softech.localLevel.request.CitizenCharterCreationRequest;
import com.softech.localLevel.request.CitizenCharterEditRequest;
import com.softtech.localLevel.response.CitizenCharterResponse;
import com.softtech.localLevel.service.CitizenCharterService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/citizenCharter")
public class CitizenCharterController {
	
	@Autowired
	private CitizenCharterService citizenCharterService;
	
	@ApiOperation(value="Post Citizen Charter",notes="Api to post the citizen Charter")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> postCitizenCharter(@RequestBody CitizenCharterCreationRequest citizenCharterCreationRequest){
		citizenCharterService.postCitizenCharter(citizenCharterCreationRequest);
		return new ResponseEntity<Object>("Posted Successfully",HttpStatus.CREATED);
	}
	
	@ApiOperation(value="Show Citizen Charter Details")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getCitizenCharter(){
		List<CitizenCharterResponse> cz=citizenCharterService.getCitizenCharter();
		return new ResponseEntity<Object>(cz,HttpStatus.OK);
		
		
	}
	
	@ApiOperation(value="Citizen Charter",notes="Api to post the citizen Charter")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Object> EditCitizenCharter(@RequestHeader Long id,@RequestBody CitizenCharterEditRequest citizenCharterCreationRequest){
		citizenCharterService.editCitizenCharter(id,citizenCharterCreationRequest);
		return new ResponseEntity<Object>("Edited Successfully",HttpStatus.CREATED);
	}
	
	@ApiOperation(value="Delete Citizen Charter",notes="Api to post the citizen Charter")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Object> DeleteCitizenCharter(@RequestHeader Long id){
		citizenCharterService.deleteCitizenCharter(id);
		return new ResponseEntity<Object>("Deleted Successfully",HttpStatus.CREATED);
	}
	

}
