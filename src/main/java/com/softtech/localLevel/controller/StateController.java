package com.softtech.localLevel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.localLevel.dto.StateDetailDto;
import com.softtech.localLevel.service.StateService;


@RestController
@RequestMapping("/rest/states")

public class StateController {
	
	@Autowired
	private StateService stateService;

	/*To display state details*/

	@RequestMapping(value = "/{state:.+}", method = RequestMethod.GET)

	public ResponseEntity<Object> listAllDistricts(@PathVariable("state") String state) {

		StateDetailDto stateDetaildto = stateService.getStateDetails(state);
		return new ResponseEntity<Object>(stateDetaildto, HttpStatus.OK);
	}

}
