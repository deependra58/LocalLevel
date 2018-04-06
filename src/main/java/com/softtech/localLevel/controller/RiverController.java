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
import com.softtech.localLevel.model.Rivers;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.RiversRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.RiversResponseDto;
import com.softtech.localLevel.service.RiversService;
import com.softtech.localLevel.util.Status;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/rivers")
public class RiverController {

	@Autowired
	private RiversRepository riverRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private RiversService riversService;
	
	
	@ApiOperation(value = "Upload an excel file for Natural Resources-Rivers")
	@RequestMapping(value = "/uploadNaturalResources/rivers", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForRiver(@RequestParam("rivers") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

		Rivers nr = riverRepository.findByRiver("Karnali");
		if (nr != null) {
			throw new AlreadyExistException("River Files Already uploaded!");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Rivers naturalResources = new Rivers();
				String string0 = row.getCell(3).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				State state = stateRepository.findByState(string0);
				// naturalResources.setDistrict(district);
				naturalResources.setState(state);

				String string1 = row.getCell(1).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				naturalResources.setRiver(string1);

				String string2 = row.getCell(4).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				naturalResources.setDescription(string2);

				naturalResources.setStatus(Status.ACTIVE);

				riverRepository.save(naturalResources);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Rivers file  uploaded Successfully!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Post rivers Manually", notes = "Posting rivers")
	@RequestMapping(value = "addRiver", method = RequestMethod.POST)
	public ResponseEntity<Object> postRiver(@RequestParam String river_name, @RequestHeader String district,@RequestParam String description) {
		Rivers ns = riversService.postRiver(river_name, district,description);
		return new ResponseEntity<Object>(ns.getRiver() + " River added successuflly", HttpStatus.OK);

	}
	
	@ApiOperation(value = "Get Rivers")
	@RequestMapping(value = "getRivers/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getRivers(@PathVariable String state) {

		List<RiversResponseDto> riversResponseDtoList = riversService.getRivers(state);
		return new ResponseEntity<Object>(riversResponseDtoList, HttpStatus.OK);

	}


}
