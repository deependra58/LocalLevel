package com.softtech.localLevel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.localLevel.model.Infrastructure;
import com.softtech.localLevel.response.AirportsResponseDto;
import com.softtech.localLevel.response.HospitalResponseDto;
import com.softtech.localLevel.response.HydropowerResponseDto;
import com.softtech.localLevel.response.IndustriesResponseDto;
import com.softtech.localLevel.service.InfrastructureService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/infrastructure")
public class InfrastructureController {

	@Autowired
	private InfrastructureService infrastructureService;

	@ApiOperation(value = "Post Airports ")
	@RequestMapping(value = "addAirports", method = RequestMethod.POST)
	public ResponseEntity<Object> postAirports(@RequestParam String airportName, @RequestParam String district) {
		Infrastructure infra = infrastructureService.postAirports(airportName, district);
		return new ResponseEntity<Object>(infra.getAirports() + " posted successfully", HttpStatus.OK);

	}

	@ApiOperation(value = "Post industries")
	@RequestMapping(value = "addIndustries", method = RequestMethod.POST)
	public ResponseEntity<Object> postIndustry(@RequestParam String industryName, @RequestParam String district) {
		Infrastructure infra = infrastructureService.postIndustry(industryName, district);

		return new ResponseEntity<Object>(infra.getIndustries() + " posted Successfully", HttpStatus.OK);

	}

	@ApiOperation(value = "Post hydropower")
	@RequestMapping(value = "addHydropower", method = RequestMethod.POST)
	public ResponseEntity<Object> postHydropower(@RequestParam String hydropowerName, @RequestParam String district,@RequestParam String capacity) {
		Infrastructure infra = infrastructureService.postHydropower(hydropowerName, district,capacity);
		System.out.println("Hydropower name:" + infra.getHydropower());
		return new ResponseEntity<Object>(infra.getHydropower() + " posted Successfully", HttpStatus.OK);

	}

	@ApiOperation(value = "Post hospital")
	@RequestMapping(value = "addHospital", method = RequestMethod.POST)
	public ResponseEntity<Object> posthospital(@RequestParam String hospitalName, @RequestParam String district,@RequestParam String hospitalContactNumber) {
		Infrastructure infra = infrastructureService.postHospital(hospitalName, district,hospitalContactNumber);
		return new ResponseEntity<Object>(infra.getHospital() + " posted Successfully", HttpStatus.OK);

	}

	@ApiOperation(value = "get Airports")
	@RequestMapping(value = "getAirport/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAirport(@PathVariable String state) {
		List<AirportsResponseDto> airportResponseDto = infrastructureService.getAirports(state);
		return new ResponseEntity<Object>(airportResponseDto, HttpStatus.OK);

	}
	
	@ApiOperation(value="get industries")
	@RequestMapping(value="getIndustries/{state:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getIndustries(@PathVariable String state){
		
		List<IndustriesResponseDto> industriesResponseDto=infrastructureService.getIndustries(state);
		return new ResponseEntity<Object>(industriesResponseDto, HttpStatus.OK);
		
	}
	
	@ApiOperation(value="get Hydropower")
	@RequestMapping(value="getHydropower/{state:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getHydropower(@PathVariable String state){
		
		List<HydropowerResponseDto> hydropowerResponseDtos=infrastructureService.getHydropower(state);
		return new ResponseEntity<Object>(hydropowerResponseDtos, HttpStatus.OK);
		
	}
	
	@ApiOperation(value="get Hospital")
	@RequestMapping(value="getHospital/{state:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getHospital(@PathVariable String state){
		
		List<HospitalResponseDto> hospitalResponseDtos=infrastructureService.getHospitalDetail(state);
		return new ResponseEntity<Object>(hospitalResponseDtos, HttpStatus.OK);
		
	}
	
	
	

}
