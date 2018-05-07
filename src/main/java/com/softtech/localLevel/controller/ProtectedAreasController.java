package com.softtech.localLevel.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softtech.localLevel.model.District;

import com.softtech.localLevel.model.ProtectedAreas;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.ProtectedAreaRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.ProtectedAreaResponseDto;
import com.softtech.localLevel.service.ProtectedAreasService;
import com.softtech.localLevel.util.Status;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/protectedAreas")
public class ProtectedAreasController {

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private ProtectedAreaRepository protectedAreaRepository;
	
	@Autowired
	private ProtectedAreasService protectedAreasService;

	@ApiOperation(value = "Upload an excel file for Natural Resources-waterfalls")
	@RequestMapping(value = "/uploadNaturalResources/protectedAreas", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetFor(@RequestParam("protectedAreas") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

		ProtectedAreas nr = protectedAreaRepository.findByProtectedAreas("Chitwan National Park");
		if (nr != null) {
			throw new AlreadyExistException("Protected Areas  Files Already uploaded!");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				ProtectedAreas naturalResources = new ProtectedAreas();
				String string0 = row.getCell(3).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				District district = districtRepository.findByDistrict(string0);
				State state = stateRepository.findById(district.getState().getId());
				naturalResources.setDistrict(district);
				naturalResources.setState(state);
				
				String string1 = row.getCell(1).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				naturalResources.setProtectedAreas(string1);
				
				String string2 = row.getCell(4).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				naturalResources.setDescription(string2);
				
				String string3 = row.getCell(2).toString();
				byte[] u3 = string3.getBytes("UTF-8");
				string3 = new String(u3, "UTF-8");
				naturalResources.setArea(string3);

				naturalResources.setStatus(Status.ACTIVE);

				protectedAreaRepository.save(naturalResources);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Protected Areas file  uploaded Successfully!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Post protectedAreas Manually", notes = "Posting protected Areas")
	@RequestMapping(value = "addProtectedAreas", method = RequestMethod.POST)
	public ResponseEntity<Object> postProtectedAreas(@RequestParam String protectedArea_name,
			@RequestHeader String district,@RequestParam String description, @RequestParam String area) {
		ProtectedAreas ns = protectedAreasService.postProtectedArea(protectedArea_name, district,description,area);
		return new ResponseEntity<Object>(ns.getProtectedAreas() + " protected Area added successuflly", HttpStatus.OK);

	}
	
	@ApiOperation(value = "Get protected Areas")
	@RequestMapping(value = "getProtectedArea/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getProtectedArea(@PathVariable String state) {

		List<ProtectedAreaResponseDto> protectedAreaResponseDtoList = protectedAreasService.getProtectedArea(state);
		return new ResponseEntity<Object>(protectedAreaResponseDtoList, HttpStatus.OK);

	}


}
