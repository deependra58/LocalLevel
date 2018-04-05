package com.softtech.localLevel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.localLevel.model.NaturalResources;
import com.softtech.localLevel.response.LakesResponseDto;
import com.softtech.localLevel.response.MountainsResponseDto;
import com.softtech.localLevel.response.ProtectedAreaResponseDto;
import com.softtech.localLevel.response.RiversResponseDto;
import com.softtech.localLevel.response.WaterfallResponseDto;
import com.softtech.localLevel.service.NaturalResourcesService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/naturalResources")

public class NaturalResourcesController {
	@Autowired
	private NaturalResourcesService naturalResourcesService;

	@ApiOperation(value = "Post the Mountain Manually", notes = "Posting mountain")
	@RequestMapping(value = "addMountain", method = RequestMethod.POST)
	public ResponseEntity<Object> postMountains(@RequestParam String mountain_name,@RequestParam String mountainHeight,
			@RequestHeader String district) {
		NaturalResources ns=naturalResourcesService.postMountain(mountain_name, mountainHeight,district);
		return new ResponseEntity<Object>(ns.getMountain()+" Mountain added successuflly", HttpStatus.OK);

	}
	
	@ApiOperation(value = "Post rivers Manually", notes = "Posting rivers")
	@RequestMapping(value = "addRiver", method = RequestMethod.POST)
	public ResponseEntity<Object> postRiver(@RequestParam String river_name,
			@RequestHeader String district) {
		NaturalResources ns=naturalResourcesService.postRiver(river_name, district);
		return new ResponseEntity<Object>(ns.getRiver()+" River added successuflly", HttpStatus.OK);

	}
	
	@ApiOperation(value = "Post lakes Manually", notes = "Posting lakes")
	@RequestMapping(value = "addLakes", method = RequestMethod.POST)
	public ResponseEntity<Object> postlakes(@RequestParam String lake_name,
			@RequestHeader String district) {
		NaturalResources ns=naturalResourcesService.postLake(lake_name, district);
		return new ResponseEntity<Object>(ns.getLake()+ " Lake added successuflly", HttpStatus.OK);

	}
	
	@ApiOperation(value = "Post waterfalls Manually", notes = "Posting waterfall")
	@RequestMapping(value = "addWaterfall", method = RequestMethod.POST)
	public ResponseEntity<Object> postwaterfall(@RequestParam String waterfall_name,
			@RequestHeader String district) {
		NaturalResources ns=naturalResourcesService.postWaterfall(waterfall_name, district);
		return new ResponseEntity<Object>(ns.getWaterfalls()+ " waterfall added successuflly", HttpStatus.OK);

	}
	
	@ApiOperation(value = "Post protectedAreas Manually", notes = "Posting waterfall")
	@RequestMapping(value = "addProtectedAreas", method = RequestMethod.POST)
	public ResponseEntity<Object> postProtectedAreas(@RequestParam String protectedArea_name,
			@RequestHeader String district) {
		NaturalResources ns=naturalResourcesService.postProtectedArea(protectedArea_name, district);
		return new ResponseEntity<Object>(ns.getProtectedAreas()+" protected Area added successuflly", HttpStatus.OK);

	}
	
	@ApiOperation(value="Get Mountains")
	@RequestMapping(value="getMountains/{state:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getMountains(@PathVariable String state){
		
		List<MountainsResponseDto> mountainsResponseDtoList=naturalResourcesService.getMountain(state);
		return new ResponseEntity<Object> (mountainsResponseDtoList, HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Get Rivers")
	@RequestMapping(value="getRivers/{state:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getRivers(@PathVariable String state){
		
		List<RiversResponseDto> riversResponseDtoList=naturalResourcesService.getRivers(state);
		return new ResponseEntity<Object> (riversResponseDtoList, HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Get Lakes")
	@RequestMapping(value="getLakes/{state:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getLakes(@PathVariable String state){
		
		List<LakesResponseDto> lakesResponseDtoList=naturalResourcesService.getLakes(state);
		return new ResponseEntity<Object> (lakesResponseDtoList, HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Get waterfall")
	@RequestMapping(value="getWaterfall/{state:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getWaterfall(@PathVariable String state){
		
		List<WaterfallResponseDto> waterfallResponseDtoList=naturalResourcesService.getWaterfall(state);
		return new ResponseEntity<Object> (waterfallResponseDtoList, HttpStatus.OK);
		
	}
	
	
	@ApiOperation(value="Get protected Areas")
	@RequestMapping(value="getProtectedArea/{state:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getProtectedArea(@PathVariable String state){
		
		List<ProtectedAreaResponseDto> protectedAreaResponseDtoList=naturalResourcesService.getProtectedArea(state);
		return new ResponseEntity<Object> (protectedAreaResponseDtoList, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	

}
