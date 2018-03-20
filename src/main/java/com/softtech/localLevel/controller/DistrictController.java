package com.softtech.localLevel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.localLevel.response.DistrictDetailsDto;
import com.softtech.localLevel.response.DistrictResponseDto;
import com.softtech.localLevel.service.DistrictService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/districts")
@Api(value = "District controller", description = "Operation about District  ")

public class DistrictController {

	@Autowired
	private DistrictService districtService;

	@ApiOperation(value = "List districts from state")
	@RequestMapping(value = "state/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> listAllDistricts(@PathVariable("state") String state) {

		List<DistrictResponseDto> districtDtoList = districtService.listAllDistricts(state);
		return new ResponseEntity<Object>(districtDtoList, HttpStatus.OK);
	}
	
	@ApiOperation(value="Show district details")
	@RequestMapping(value="district/{district:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getDistrictDetail(@PathVariable String district){
		
		DistrictDetailsDto districtDetailsDto= districtService.getDistrictDetails(district);
		return new ResponseEntity<Object>(districtDetailsDto, HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Post District Picture")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> postDistrictPicture(@RequestBody String string, Long districtId){
		
		districtService.postDistrictPicture(string,districtId);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
