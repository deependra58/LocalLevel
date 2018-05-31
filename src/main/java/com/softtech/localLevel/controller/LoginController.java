package com.softtech.localLevel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.localLevel.dto.LoginRequestDto;
import com.softtech.localLevel.dto.LoginResponseDto;
import com.softtech.localLevel.service.LoginService;

import io.swagger.annotations.ApiOperation;
/**
 * <<This is the controller for Login>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 March 2018
*/
@RestController
@RequestMapping("rest/v1")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@ApiOperation(value="Login", notes="Api to login")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> doLogin(@RequestBody LoginRequestDto loginRequestDto){
		LoginResponseDto loginResponseDto=  loginService.doLogin(loginRequestDto);
		return new ResponseEntity<Object>(loginResponseDto.getToken(),  HttpStatus.OK);
		
	}

}
