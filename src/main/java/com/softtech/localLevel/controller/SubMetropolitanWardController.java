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

import com.softech.localLevel.request.SubMetropolitanWardEditRequest;
import com.softech.localLevel.request.SubMetropolitanWardCreationRequest;
import com.softtech.localLevel.response.SubMetropolitanWardResponse;
import com.softtech.localLevel.service.SubMetropolitanWardService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/subMetropolitans")
public class SubMetropolitanWardController {
	
	@Autowired
	private SubMetropolitanWardService SubMetropolitanWardService;

	/* Api to post  SubMetropolitan Ward manually */
	@ApiOperation(value = "Post SubMetropolitan ward Manually")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> postSubMetropolitanWard(
			@RequestBody SubMetropolitanWardCreationRequest subMetropolitanWardCreationRequest) {

		SubMetropolitanWardService.postSubMetropolitanWard(subMetropolitanWardCreationRequest);
		return new ResponseEntity<Object>("SubMetropolitan Ward Posted Successfully", HttpStatus.OK);

	}
	
	@ApiOperation(value="Get list of old wards from new ward")
	@RequestMapping(value="subMetropolitan/{subMetropolitan:.+}",method=RequestMethod.GET)
	public ResponseEntity<Object> getSubMetropolitanWard(@PathVariable String subMetropolitan, @RequestHeader Long newWardId){
		
		List<SubMetropolitanWardResponse> rmwr=SubMetropolitanWardService.getOldVdc(subMetropolitan,newWardId);
		return new ResponseEntity<Object>(rmwr,HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Get the new ward from old ward")
	@RequestMapping(value="OldVdcName/{OldVdcName:.+}",method=RequestMethod.GET)
	public ResponseEntity<Object> getNewVdcWard(@PathVariable String OldVdcName, @RequestHeader Long oldWardId){
		
		SubMetropolitanWardResponse rmwr=SubMetropolitanWardService.getOldVdcInfo(OldVdcName,oldWardId);
		return new ResponseEntity<Object>(rmwr,HttpStatus.OK);
	}
	
	@ApiOperation(value="Edit the subMetropolitan ward Info")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> editSubMetropolitanWard(@RequestHeader Long id,
			@RequestBody SubMetropolitanWardEditRequest subMetropolitanWardEditRequest) {
		SubMetropolitanWardService.editSubMetropolitan(id,subMetropolitanWardEditRequest);
		return new ResponseEntity<Object>("Edited Successfully",HttpStatus.OK);
		}
	
	
	@ApiOperation(value="Delete the subMetropolitan ward Info")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteInfo(@RequestHeader Long id){
		SubMetropolitanWardService.deleteInfo(id);
		return new ResponseEntity<Object>("Deleted Successfully",HttpStatus.OK);
		

}
}
