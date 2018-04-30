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

import com.softtech.localLevel.model.Attraction;
import com.softtech.localLevel.response.AttractionResponseDto;
import com.softtech.localLevel.service.AttractionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/famousFor")
public class AttractionController {
	
	@Autowired 
	private AttractionService attractionService;
	
	@ApiOperation(value="Post famous items")
	@RequestMapping(value="famousFor", method=RequestMethod.POST)
	public ResponseEntity<Object> postFamousFor(@RequestParam String item, @RequestParam String district,@RequestParam String localAddress,@RequestParam String description){
		Attraction ff=attractionService.postFamousFor(item,district,description,localAddress);
		return new ResponseEntity<Object>(ff.getItem()+" posted Successfully!",HttpStatus.OK);
	}
	
	@ApiOperation(value="get Attraction")
	@RequestMapping(value="getAttraction/{state:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getAttraction(@PathVariable String state){
		
		List<AttractionResponseDto> attractionResponseDto=attractionService.getAttaction(state);
		return new ResponseEntity<Object>(attractionResponseDto, HttpStatus.OK);
		
	}

}
