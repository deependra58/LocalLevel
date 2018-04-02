//package com.softtech.localLevel.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.softtech.localLevel.response.WardResponseDto;
//import com.softtech.localLevel.service.WardService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
//@RestController
//@RequestMapping("/rest/wards")
//@Api(value="Ward Controller", description="Operation about ward")
//public class WardController {
//	
//	
//	@Autowired
//	private WardService wardService;
//	
//	@ApiOperation(value="shows the new Ward and new Vdc from old wardId and old Vdc name")
//	@RequestMapping(value="ward/{oldWardId:.+}/{oldVdcName:.+}",method=RequestMethod.GET)
//	public ResponseEntity<Object> getWard(@PathVariable Long oldWardId, @PathVariable String oldVdcName){
//		System.out.println("got into");
//		WardResponseDto wardResponseDto=wardService.getWard(oldWardId,oldVdcName);
//		return new ResponseEntity<Object> (wardResponseDto, HttpStatus.OK);
//		
//		
//	}
//	
//
//}
