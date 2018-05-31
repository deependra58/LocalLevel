package com.softtech.localLevel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softech.localLevel.request.CitizenCharterCreationRequest;
import com.softech.localLevel.request.CitizenCharterEditRequest;
import com.softtech.localLevel.response.CitizenCharterResponse;
import com.softtech.localLevel.service.CitizenCharterService;

import io.swagger.annotations.ApiOperation;
/**
 * <<This is the controller for Citizen Charter>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 March 2018
*/
@RestController
@RequestMapping("/rest/citizenCharters")
public class CitizenCharterController {
	
	@Autowired
	private CitizenCharterService citizenCharterService;
	
	/*Api to post citizen charter manually*/
	
	@ApiOperation(value="Post Citizen Charter",notes="Api to post the citizen Charter")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> postCitizenCharter(@RequestBody CitizenCharterCreationRequest citizenCharterCreationRequest,@RequestParam (required=false) String metropolitan,@RequestParam(required=false) String municipality, @RequestParam(required=false) String subMetropolitan, @RequestParam(required=false) String ruralMunicipality ){
		citizenCharterService.postCitizenCharter(citizenCharterCreationRequest,metropolitan,municipality,subMetropolitan,ruralMunicipality);
		return new ResponseEntity<Object>("Posted Successfully",HttpStatus.CREATED);
	}
	
	/*Api to get citizen charter details*/
	
	@ApiOperation(value="Show Citizen Charter Details by District and Vdc name")
	@RequestMapping(value="/{district:.+}",method=RequestMethod.GET)
	public ResponseEntity<Object> getCitizenCharter(@PathVariable String district , @RequestParam (required=false) String metropolitan,@RequestParam(required=false) String subMetropolitan, @RequestParam (required=false) String municipality, @RequestParam (required=false) String ruralMunicipality){
		CitizenCharterResponse cz=citizenCharterService.getCitizenCharter(district,metropolitan,subMetropolitan,subMetropolitan,ruralMunicipality);
		System.out.println(cz.getComplainTo());
		return new ResponseEntity<Object>(cz,HttpStatus.OK);
		
		
	}
	
	/*Api to edit citizen charter detail*/
	
	@ApiOperation(value="Citizen Charter",notes="Api to post the citizen Charter")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Object> EditCitizenCharter(@RequestHeader Long id,@RequestBody CitizenCharterEditRequest citizenCharterCreationRequest){
		citizenCharterService.editCitizenCharter(id,citizenCharterCreationRequest);
		return new ResponseEntity<Object>("Edited Successfully",HttpStatus.CREATED);
	}
	
	/*Api to delete citizen charter*/
	
	@ApiOperation(value="Delete Citizen Charter",notes="Api to post the citizen Charter")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Object> DeleteCitizenCharter(@RequestHeader Long id){
		citizenCharterService.deleteCitizenCharter(id);
		return new ResponseEntity<Object>("Deleted Successfully",HttpStatus.CREATED);
	}
	

}
