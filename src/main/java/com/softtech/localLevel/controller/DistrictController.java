package com.softtech.localLevel.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softech.localLevel.request.DistrictCreationRequest;
import com.softech.localLevel.request.DistrictEditRequest;
import com.softtech.localLevel.dto.DistrictDetailsDto;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.DistrictResponseDto;
import com.softtech.localLevel.response.LocalLevelResponseDto;
import com.softtech.localLevel.response.MetropolitanResponseDto;
import com.softtech.localLevel.response.MunicipalityResponseDto;
import com.softtech.localLevel.response.RuralMunicipalResponseDto;
import com.softtech.localLevel.response.SubMetropolitanResponseDto;
import com.softtech.localLevel.service.DistrictService;
import com.softtech.localLevel.util.LocalLevelType;
import com.softtech.localLevel.util.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * <<This is the controller for District>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 March 2018
*/
@RestController
@RequestMapping("/rest/districts")
@Api(value = "District controller", description = "Operation about District  ")

public class DistrictController {

	@Autowired
	private DistrictService districtService;

	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private StateRepository stateRepository;

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Upload an excel file for District")
	@RequestMapping(value = "/uploadDistrict", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheet(@RequestParam("Districts") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

		int k = 0, counter = 2;
		String temp = null;
		String[] temp0 = new String[35083];
		temp0[0] = null;
		String[] temp1 = new String[35083];
		temp1[0] = null;
		String[] temp2 = new String[35083];
		temp2[0] = null;
		String[] temp3 = new String[35083];
		temp3[0] = null;
		String[] temp4 = new String[35083];
		temp4[0] = null;
		String[] temp5 = new String[35083];
		temp5[0] = null;
		
		District dist=districtRepository.findByDistrict("Jhapa");
		if(dist!=null) {
			
			throw new AlreadyExistException("District file has been already uploaded into the database.");
		}
		

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {
				
				

				District district = new District();
				String string0 = row.getCell(1).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				district.setDistrict(string0);

				String string1 = row.getCell(2).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				district.setHeadquater(string1);
				
				String string01=row.getCell(3).toString();
				byte[] u01=string01.getBytes("UTF-8");
				string01=new String(u01, "UTF-8");
				State state=stateRepository.findByState(string01);
				if(state==null) {
					throw new NotFoundException("State with name "+string01+" couldn't be found!");
				}
				district.setState(state);

				String string2 = row.getCell(4).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				String[] a=string2.split(Pattern.quote("."));
				district.setArea(a[0]);

				String string3 = row.getCell(5).toString();
				byte[] u3 = string3.getBytes("UTF-8");
				string3 = new String(u3, "UTF-8");
				String[] a2=string3.split(Pattern.quote("."));
				district.setPopulation(a2[0]);

				district.setLocalLevelType(LocalLevelType.DISTRICT);
				district.setStatus(Status.ACTIVE);

				districtRepository.save(district);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("District uploaded",HttpStatus.OK);
	}

	@ApiOperation(value = "List districts from state")
	@RequestMapping(value = "state/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> listAllDistricts(@PathVariable("state") String state) {
		 System.out.println("Hello world!!");
		List<DistrictResponseDto> districtDtoList = districtService.listAllDistricts(state);
		return new ResponseEntity<Object>(districtDtoList, HttpStatus.OK);
	}

	@ApiOperation(value = "Post District detail Manally", notes = "Post district detail manually")
	@RequestMapping(value = "district", method = RequestMethod.POST)
	public ResponseEntity<Object> postDistrictDetail(@RequestBody DistrictCreationRequest districtCreationRequest) {

		districtService.postDistrictDetail(districtCreationRequest);
		return new ResponseEntity<Object>(HttpStatus.OK);

	}

	@ApiOperation(value = "Edit District Information", notes = "Edit district information")
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> editDistrictDetail(@RequestBody DistrictEditRequest districtEditRequest,
			@RequestHeader(required = false) Long districtId,
			@RequestParam(value = "search by district", required = false) String district) {

		District ds = districtService.editDistrict(districtEditRequest, district, districtId);
		return new ResponseEntity<Object>(ds, HttpStatus.OK);

	}

	@ApiOperation(value = "Show district details")
	@RequestMapping(value = "district/{district:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getDistrictDetail(@PathVariable String district) {

		DistrictDetailsDto districtDetailsDto = districtService.getDistrictDetails(district);
		return new ResponseEntity<Object>(districtDetailsDto, HttpStatus.OK);

	}
	
	@ApiOperation(value="Show all the local level belonging to the particular district")
	@RequestMapping(value="localLevel/{district:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getLocalLevel(@PathVariable String district){
		
		List<RuralMunicipalResponseDto> ruralMunicipalResponseDto=districtService.getRuralMunicipal(district);
		List<MunicipalityResponseDto> municipalityResponseDto=districtService.getMunicipality(district);
		List<MetropolitanResponseDto> metropolitanResponseDto=districtService.getMetropolitan(district);
		List<SubMetropolitanResponseDto> subMetropolitanResponseDto=districtService.getSubMetropolitan(district);
		 LocalLevelResponseDto localLevelResponseDto=new  LocalLevelResponseDto(ruralMunicipalResponseDto,municipalityResponseDto,subMetropolitanResponseDto,metropolitanResponseDto);
		//List<LocalLevelResponseDto> localLevelResponseDto=districtService.getLocalLevel(district);
		return new ResponseEntity<Object>(localLevelResponseDto,HttpStatus.OK);
		
	}
	

	@ApiOperation(value = "Post District Picture")
	@RequestMapping(value="districtImgage/{district:.+}",method = RequestMethod.POST)
	public ResponseEntity<Object> postDistrictPicture(@RequestBody String string, @RequestHeader String district) {

		districtService.postDistrictPicture(string, district);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
