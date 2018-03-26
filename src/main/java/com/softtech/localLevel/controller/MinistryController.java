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

import com.softech.localLevel.request.MinistryCreationDto;
import com.softech.localLevel.request.MinistryEditRequest;
import com.softtech.localLevel.model.Ministry;
import com.softtech.localLevel.response.MinistryResponseDto;
import com.softtech.localLevel.service.MininstryService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="rest/ministry")
public class MinistryController {
	
	@Autowired
	private MininstryService ministryService;
	
	@ApiOperation(value="Post Central Ministry detail", notes=" Post Central Ministry Detail")
	@RequestMapping(value="ministries/central", method=RequestMethod.POST)
	public ResponseEntity<Object> postCentralMinistryInfo(@RequestBody MinistryCreationDto minsitryCreationDto){
		
		Ministry ms=ministryService.createCentralMinistry(minsitryCreationDto);
		return new ResponseEntity<Object>("New Ministry added: ID="+ms.getId(),HttpStatus.OK);
	}
	
	@ApiOperation(value="Post Local Ministry detail", notes=" Post Local  Ministry Detail")
	@RequestMapping(value="ministries/local", method=RequestMethod.POST)
	public ResponseEntity<Object> postLocalMinistryInfo(@RequestBody MinistryCreationDto minsitryCreationDto){
		
		Ministry ms=ministryService.createLocalMinistry(minsitryCreationDto);
		return new ResponseEntity<Object>("New Ministry added: ID="+ms.getId(),HttpStatus.OK);
	}
	
	
	@ApiOperation(value="Delete Central Mininstry detail", notes="Delete Central ministry Detail")
	@RequestMapping(value="CentralMinistries/{ministryName:.+}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCentralMinistry(@PathVariable String ministryName){
		ministryService.deleteMinistry(ministryName);
		return new ResponseEntity<Object>("Ministry Deleted",HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Delete Mininstry detail", notes="Delete ministry Detail")
	@RequestMapping(value="LocalMinistries/{ministryName:.+}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteLocalMinistry(@PathVariable String ministryName){
		ministryService.deleteLocalMinistry(ministryName);
		return new ResponseEntity<Object>("Ministry Deleted",HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Show All Central Ministry Name", notes="Display All Central ministry Name")
	@RequestMapping(value="centralMinistries/showMinistry", method=RequestMethod.GET)
	public ResponseEntity<Object> getAllCentralMinistry(){
		
		List<MinistryResponseDto> ministryList=ministryService.getCentralMinistries();
		return new ResponseEntity<Object>(ministryList,HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Show All Local Ministry Name", notes="Display All Local ministry Name")
	@RequestMapping(value="localMinistries/showMinistry", method=RequestMethod.GET)
	public ResponseEntity<Object> getAllLocalMinistry(){
		
		List<MinistryResponseDto> ministryList=ministryService.getLocalMinistries();
		return new ResponseEntity<Object>(ministryList,HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Edit central Ministry information", notes="Edit central Ministry Information")
	@RequestMapping(value="centralMinistries/editMinistry", method=RequestMethod.PUT)
	public ResponseEntity<Object> editCentralMinistry(@RequestBody MinistryEditRequest ministryEditRequest , @RequestHeader String ministryName ){
		
		Ministry ministry= ministryService.editCentralMinistry(ministryEditRequest,  ministryName);
		return new ResponseEntity<Object>(ministry,HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Edit local Ministry information", notes="Edit local Ministry Information")
	@RequestMapping(value="localMinistries/editMinistry", method=RequestMethod.PUT)
	public ResponseEntity<Object> editLocalMinistry(@RequestBody MinistryEditRequest ministryEditRequest , @RequestHeader String ministryName ){
		
		Ministry ministry= ministryService.editLocalMinistry(ministryEditRequest,  ministryName);
		return new ResponseEntity<Object>(ministry,HttpStatus.OK);
		
	}

}
