package com.softtech.localLevel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.localLevel.response.OldVdcResponseDto;
import com.softtech.localLevel.service.OldVdcService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/oldVdcs")
@Api(value = "Old Vdc Controller", description = "Operations about Old Vdcs")
public class OldVdcController {

	@Autowired
	private OldVdcService oldVdcService;

	@ApiOperation(value = "Shows the old vdcs from district", notes = "Shows the old vdcs corresponding to district")
	@RequestMapping(value = "district/{district:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> listAllOldVdc(@PathVariable String district) {

		List<OldVdcResponseDto> OldVdcDtoList = oldVdcService.listAllOldVdc(district);
		return new ResponseEntity<Object>(OldVdcDtoList, HttpStatus.OK);
	}

}
