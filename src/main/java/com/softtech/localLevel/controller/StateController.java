package com.softtech.localLevel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.localLevel.dto.StateDetailDto;
import com.softtech.localLevel.service.StateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/rest/states")
@Api(value="State Controller", description="Operation about states")
public class StateController {
	
	@Autowired
	private StateService stateService;

	
	@ApiOperation(value="Shows the state details", notes="Shows the details regarding state")
	@RequestMapping(value = "state/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> listAllDistricts(@PathVariable("state") String state) {
		StateDetailDto stateDetaildto = stateService.getStateDetails(state);
		return new ResponseEntity<Object>(stateDetaildto, HttpStatus.OK);
	}
	
	
	@ApiOperation(value="Post the state picture", notes="Post the state picture")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> postPicture(@RequestBody String string ,@RequestHeader Long stateId){
	stateService.postPicture(string,stateId);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
