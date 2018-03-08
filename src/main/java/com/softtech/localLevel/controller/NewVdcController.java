package com.softtech.localLevel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.localLevel.dto.VdcDetailDto;
import com.softtech.localLevel.response.NewVdcResponseDto;
import com.softtech.localLevel.service.NewVdcService;

@RestController
@RequestMapping("/newVdcs")
public class NewVdcController {

	@Autowired
	private NewVdcService newVdcService;

	/* To display all the new Vdc associated with district name */

	@RequestMapping(value = "/list/{district:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> ListAllNewVdc(@PathVariable String district) {

		List<NewVdcResponseDto> newVdcDtoList = newVdcService.ListAllNewVdc(district);
		return new ResponseEntity<Object>(newVdcDtoList, HttpStatus.OK);
	}

	/* To display a new Vdc to which the given old vdc belongs to */
	@RequestMapping(value = "/{oldVdc:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getNewVdc(@PathVariable String oldVdc) {

		NewVdcResponseDto newVdcResponseDto = newVdcService.getNewVdc(oldVdc);
		return new ResponseEntity<Object>(newVdcResponseDto, HttpStatus.OK);
	}

	/* To display detail about the vdc */
	@RequestMapping(value = "/newVdcDetails/{oldVdcName:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getDetails(@PathVariable String oldVdcName) {

		// System.out.println("The id is" + oldVdcName);
		VdcDetailDto vdcDetailDto = newVdcService.getVdcDetail(oldVdcName);
		return new ResponseEntity<Object>(vdcDetailDto, HttpStatus.OK);

	}

}