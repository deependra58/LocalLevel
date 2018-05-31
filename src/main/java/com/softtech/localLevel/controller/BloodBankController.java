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

import com.softech.localLevel.request.BloodBankCreationRequest;
import com.softtech.localLevel.response.BloodBankResponse;
import com.softtech.localLevel.service.BloodBankService;

import io.swagger.annotations.ApiOperation;
/**
 * <<This is the controller for Blood Bank>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 March 2018
*/
@RestController
@RequestMapping("rest/bloodBanks")
public class BloodBankController {
	
	@Autowired
	private BloodBankService bloodBankService;
	
	/*Api to post blood bank*/
	@ApiOperation(value="Post BloodBank",notes="Api to post BlodBank")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> postBloodBank(@RequestBody BloodBankCreationRequest bloodBankCreationRequest){
		
		bloodBankService.postBloodBank(bloodBankCreationRequest);
		return new ResponseEntity<Object>("Posted Successfully",HttpStatus.CREATED);
		
	}
	
	/*Api to get all blood bank by state*/
	@ApiOperation(value="Get All BloodBank ",notes="Api to get all BloodBank")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getBloodBank(@RequestParam String state){
		
		List<BloodBankResponse> bloodBankResponseList=bloodBankService.getAllBloodBank(state);
		return new ResponseEntity<Object>(bloodBankResponseList,HttpStatus.CREATED);
		
	}

}
