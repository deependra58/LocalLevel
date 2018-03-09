package com.softtech.localLevel.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.softtech.localLevel.response.DistrictResponseDto;
import com.softtech.localLevel.service.DistrictService;

@RestController
@RequestMapping("/districts")

public class DistrictController {

	@Autowired
	private DistrictService districtService;

	/* List all districts corresponding to state name */

	@RequestMapping(value = "/{state:.+}", method = RequestMethod.GET)

	public ResponseEntity<Object> listAllDistricts(@PathVariable("state") String state) {

		System.out.println("Request accepted to list all districts   " + state);
		List<DistrictResponseDto> districtDtoList = districtService.listAllDistricts(state);
		return new ResponseEntity<Object>(districtDtoList, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value="/allDistricts",method=RequestMethod.GET) public
	 * ResponseEntity<Object> listAllDistrict() {
	 * 
	 * System.out.println("Request accepted to list all districts   " );
	 * List<DistrictResponseDto> districtDtoList =
	 * districtService.listAllDistricts(); return new
	 * ResponseEntity<Object>(districtDtoList, HttpStatus.OK); }
	 */

	/*
	 * To display all the old vdc associated with district name
	 * 
	 * @RequestMapping( value="/{district:.+}",method = RequestMethod.GET) public
	 * ResponseEntity<Object> listAllOldVdc(@PathVariable String district) {
	 * LOG.debug("Request accepted to list all New VDCs."); List<OldVdcResponseDto>
	 * OldVdcDtoList = districtService.listAllOldVdc(district); return new
	 * ResponseEntity<Object>(OldVdcDtoList, HttpStatus.OK); }
	 * 
	 * To display all the new Vdc associated with district name
	 * 
	 * @RequestMapping(value="/{district_vdc:.+}",method=RequestMethod.GET) public
	 * ResponseEntity<Object> ListAllNewVdc(@PathVariable String district_vdc){
	 * 
	 * List<NewVdcResponseDto>
	 * newVdcDtoList=districtService.ListAllNewVdc(district_vdc); return new
	 * ResponseEntity<Object>(newVdcDtoList,HttpStatus.OK); }
	 */
}
