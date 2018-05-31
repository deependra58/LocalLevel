package com.softtech.localLevel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softech.localLevel.request.RuralMunicipalityWardCreationRequest;
import com.softech.localLevel.request.RuralMunicipalityWardEditRequest;
import com.softtech.localLevel.response.RuralMunicipalWardResponse;
import com.softtech.localLevel.service.RuralMunicipalityWardService;

import io.swagger.annotations.ApiOperation;

/** Here are the API for Rural Municipality Ward */

@RestController
@RequestMapping("/rest/ruralMunicipalityWards")
public class RuralMunicipalityWardController {

	@Autowired
	private RuralMunicipalityWardService ruralMunicipalityWardService;

	/* Api to post Rural Municipality Ward manually */
	@ApiOperation(value = "Post RuralMunicipality ward Manually")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> postRuralMunicipalityWard(
			@RequestBody RuralMunicipalityWardCreationRequest ruralMunicipalityWardCreationRequest) {

		ruralMunicipalityWardService.postRuralMunicipalityWard(ruralMunicipalityWardCreationRequest);
		return new ResponseEntity<Object>("Rural Municipality Ward Posted Successfully", HttpStatus.OK);

	}
	
	@ApiOperation(value="Get list of old wards from new ward")
	@RequestMapping(value="ruralMunicipality/{ruralMunicipality:.+}",method=RequestMethod.GET)
	public ResponseEntity<Object> getRuralMunicipality(@PathVariable String ruralMunicipality, @RequestHeader Long newWardId){
		
		List<RuralMunicipalWardResponse> rmwr=ruralMunicipalityWardService.getOldVdc(ruralMunicipality,newWardId);
		return new ResponseEntity<Object>(rmwr,HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Get the new ward from old ward")
	@RequestMapping(value="OldVdcName/{OldVdcName:.+}",method=RequestMethod.GET)
	public ResponseEntity<Object> getNewVdcWard(@PathVariable String OldVdcName, @RequestHeader Long oldWardId){
		
		RuralMunicipalWardResponse rmwr=ruralMunicipalityWardService.getOldVdcInfo(OldVdcName,oldWardId);
		return new ResponseEntity<Object>(rmwr,HttpStatus.OK);
	}
	
	@ApiOperation(value="Edit the ruralMunicipality ward Info")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> editRuralMunicipalityWard(@RequestHeader Long id,
			@RequestBody RuralMunicipalityWardEditRequest ruralMunicipalityWardEditRequest) {
		ruralMunicipalityWardService.editRuralMunicipalityWard(id,ruralMunicipalityWardEditRequest);
		return new ResponseEntity<Object>("Edited Successfully",HttpStatus.OK);
		}
	
	@ApiOperation(value="Delete the ruralMunicipality ward Info")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteInfo(@RequestHeader Long id){
		ruralMunicipalityWardService.deleteInfo(id);
		return new ResponseEntity<Object>("Deleted Successfully",HttpStatus.OK);
		
		
	}

	
	
	

}
