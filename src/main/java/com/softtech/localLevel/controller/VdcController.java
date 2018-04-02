package com.softtech.localLevel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		System.out.println("hello");
		System.out.println("Enter");
		 vdcService.postVdcDetails(vdc,district,ruralMunicipality,municipality,subMetropolitan,metropolitan);
		return new ResponseEntity<Object>("Posted successfully", HttpStatus.OK);

	}
	
	//value = "vdcDetails/{vdc:.+}/{district:.+}/{metropolitan:/+}/{subMetroploitan:.+}/{ruralMunicipality:'+}/{municipality:'+}",

}

