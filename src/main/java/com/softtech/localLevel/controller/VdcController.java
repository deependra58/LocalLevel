package com.softtech.localLevel.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.localLevel.service.VdcService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/vdcs")
public class VdcController {

	@Autowired
	private VdcService vdcService;

	@ApiOperation(value = "Post old vdc details manually")
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<Object> postVdcDetails( @RequestParam (required=true) String vdc, @RequestParam (required=true)
	 String district, @RequestParam(required=false) String ruralMunicipality,
	 @RequestParam(required=false) String municipality,
	 @RequestParam(required=false) String
	 subMetropolitan,@RequestParam(required=false) String metropolitan) {
		
		 vdcService.postVdcDetails(vdc,district,ruralMunicipality,municipality,subMetropolitan,metropolitan);
		return new ResponseEntity<Object>("Posted successfully", HttpStatus.OK);

	}
	
	@ApiOperation(value="Edit old vdc details")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Object> putVdcDetails(@RequestHeader Long vdcId,@RequestParam (required=true) String vdc, @RequestParam (required=true)
	 String district, @RequestParam(required=false) String ruralMunicipality,
	 @RequestParam(required=false) String municipality,
	 @RequestParam(required=false) String
	 subMetropolitan,@RequestParam(required=false) String metropolitan){
		
		 vdcService.editVdcDetails(vdcId,vdc,district,ruralMunicipality,municipality,subMetropolitan,metropolitan);
		 return new ResponseEntity<Object>("Edited successfully", HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Delete old vdc")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteVdc(@RequestParam (required=false) Long vdcId, @RequestParam (required=false) String vdc){
		vdcService.deleteVdc(vdcId,vdc);
		return new ResponseEntity<Object> ("Deleted Successfully", HttpStatus.OK);
		
	}
}

