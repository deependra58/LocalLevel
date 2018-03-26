package com.softtech.localLevel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softtech.localLevel.dto.VdcDetailDto;
import com.softtech.localLevel.response.NewVdcResponseDto;
import com.softtech.localLevel.service.NewVdcService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest/newVdcs")
@Api(value="New Vdc Controller", description="New Vdc operations")
public class NewVdcController {

	@Autowired
	private NewVdcService newVdcService;


	@ApiOperation(value="Shows new vdc from district", notes="Shows the corresponsing New Vdcs of a district")
	@RequestMapping(value = "district/{district:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> ListAllNewVdc(@PathVariable String district) {

		List<NewVdcResponseDto> newVdcDtoList = newVdcService.ListAllNewVdc(district);
		return new ResponseEntity<Object>(newVdcDtoList, HttpStatus.OK);
	}

	
	@ApiOperation(value="Shows the New vdc From old vdc", notes="Shows the new vdc to which the old vdc belongs to")
	@RequestMapping(value = "oldVdc/{oldVdc:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getNewVdc(@PathVariable String oldVdc) {

		NewVdcResponseDto newVdcResponseDto = newVdcService.getNewVdc(oldVdc);
		return new ResponseEntity<Object>(newVdcResponseDto, HttpStatus.OK);
	}
      
	

	@ApiOperation(value="Display Details about New vdc", notes="Shows the detail about New vdc from oldVdc name")
	@RequestMapping(value = "/newVdcDetails/{oldVdcName:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getDetails(@PathVariable String oldVdcName) {

		// System.out.println("The id is" + oldVdcName);
		VdcDetailDto vdcDetailDto = newVdcService.getVdcDetail(oldVdcName);		
		return new ResponseEntity<Object>(vdcDetailDto, HttpStatus.OK);

	}

}