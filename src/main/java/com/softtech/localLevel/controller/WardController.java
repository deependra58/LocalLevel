package com.softtech.localLevel.controller;

import java.lang.invoke.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.localLevel.response.WardResponseDto;
import com.softtech.localLevel.service.WardService;

@RestController
@RequestMapping("/wards")
public class WardController {
	
	
	@Autowired
	private WardService wardService;
	
	@RequestMapping(value="/{oldWardId:.+}/{oldVdcName:.+}",method=RequestMethod.GET)
	public ResponseEntity<Object> getWard(@PathVariable Long oldWardId, @PathVariable String oldVdcName){
		System.out.println("got into");
		WardResponseDto wardResponseDto=wardService.getWard(oldWardId,oldVdcName);
		return new ResponseEntity<Object> (wardResponseDto, HttpStatus.OK);
		
		
	}
	

}
