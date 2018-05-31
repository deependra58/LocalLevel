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

import com.softech.localLevel.request.MunicipalityWardCreationRequest;
import com.softech.localLevel.request.MunicipalityWardEditRequest;
import com.softtech.localLevel.response.MunicipalityWardResponse;
import com.softtech.localLevel.service.MunicipalityWardService;

import io.swagger.annotations.ApiOperation;

/*<<This is the controller for municipality ward>>
 * @Author Deependra
 * @Since 10 May ,2018
 * @Version 1.0.1
 * */
@RestController
@RequestMapping("/rest/municipalityWards")
public class MunicipalityWardController {

	@Autowired
	private MunicipalityWardService municipalityWardService;

	/* Api to post Municipality Ward manually */
	@ApiOperation(value = "Post Municipality ward Manually")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> postMunicipalityWard(
			@RequestBody MunicipalityWardCreationRequest municipalityWardCreationRequest) {

		municipalityWardService.postMunicipalityWard(municipalityWardCreationRequest);
		return new ResponseEntity<Object>("Municipality Ward Posted Successfully", HttpStatus.OK);

	}

	@ApiOperation(value = "Get list of old wards from new ward")
	@RequestMapping(value = "municipality/{municipality:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getRuralMunicipality(@PathVariable String municipality,
			@RequestHeader Long newWardId) {

		List<MunicipalityWardResponse> rmwr = municipalityWardService.getOldVdc(municipality, newWardId);
		return new ResponseEntity<Object>(rmwr, HttpStatus.OK);

	}

	@ApiOperation(value = "Get the new ward from old ward")
	@RequestMapping(value = "OldVdcName/{OldVdcName:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getNewVdcWard(@PathVariable String OldVdcName, @RequestHeader Long oldWardId) {

		MunicipalityWardResponse rmwr = municipalityWardService.getNewWard(OldVdcName, oldWardId);
		return new ResponseEntity<Object>(rmwr, HttpStatus.OK);
	}

	@ApiOperation(value = "Edit the Municipality ward Info")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> editMunicipalityWard(@RequestHeader Long id,
			@RequestBody MunicipalityWardEditRequest municipalityWardEditRequest) {
		municipalityWardService.editMunicipalityWard(id, municipalityWardEditRequest);
		return new ResponseEntity<Object>("Edited Successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Delete the Municipality ward Info")
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteInfo(@RequestHeader Long id) {
		municipalityWardService.deleteInfo(id);
		return new ResponseEntity<Object>("Deleted Successfully", HttpStatus.OK);

	}
}
