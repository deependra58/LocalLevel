package com.softtech.localLevel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softech.localLevel.request.UserCreationRequest;
import com.softtech.localLevel.model.User;
import com.softtech.localLevel.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <<This is the controller for user>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 April 2018
*/
@RestController
@RequestMapping("rest/")
@Api(value = "user Controller", description = "API for users")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Create a user", notes = "Use this API to Create a user: <ADMIN>, <SUPERADMIN>,<USER>")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestHeader Long userId,
			@RequestBody UserCreationRequest userCreationRequest) {
		User user = userService.createUser(userId, userCreationRequest);
		return new ResponseEntity<Object>(user.getId(), HttpStatus.CREATED);

	}

}
