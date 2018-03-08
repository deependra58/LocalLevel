/*package com.softtech.localLevel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.localLevel.response.DistrictResponseDto;
import com.softtech.localLevel.service.StateService;

@RestController
@RequestMapping("/states")

public class StateController {

	@Autowired
	private StateService stateService;

	@RequestMapping(value = "/{state:.+}", method = RequestMethod.GET)

	public ResponseEntity<Object> listAllDistricts(@PathVariable("state") String state) {

		System.out.println("Request accepted to list all districts   " + state);
		List<DistrictResponseDto> districtDtoList = stateService.listAllDistricts(state);
		return new ResponseEntity<Object>(districtDtoList, HttpStatus.OK);
	}

}
*/