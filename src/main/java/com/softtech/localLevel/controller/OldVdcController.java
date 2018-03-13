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

@RestController
@RequestMapping("/rest/oldVdcs")
public class OldVdcController {

	@Autowired
	private OldVdcService oldVdcService;

	/* To display all the old vdc associated with district name */

	@RequestMapping(value = "/{district:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> listAllOldVdc(@PathVariable String district) {

		List<OldVdcResponseDto> OldVdcDtoList = oldVdcService.listAllOldVdc(district);
		return new ResponseEntity<Object>(OldVdcDtoList, HttpStatus.OK);
	}

}
