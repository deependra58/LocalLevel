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
import com.softtech.localLevel.model.Airports;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.AirportsRepository;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.AirportsResponseDto;
import com.softtech.localLevel.service.AirportsService;
import com.softtech.localLevel.util.Status;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/airports")
public class AirportsController {

	@Autowired
	private AirportsService airportsService;

	@Autowired
	private AirportsRepository airportsRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private DistrictRepository districtRepository;
	
	
	@ApiOperation(value = "Upload an excel file for Natural Resources-Mountains")
	@RequestMapping(value = "/uploadAirports/airports", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForMountain(@RequestParam("Airports") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

		Airports nr = airportsRepository.findByAirportNameAndStatusNot("Tribhuvan International Airport",
				Status.DELETED);
		if (nr != null) {
			throw new AlreadyExistException("Airport Files Already uploaded!");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Airports airports = new Airports();
				String string0 = row.getCell(2).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				District district = districtRepository.findByDistrict(string0);
				State state = stateRepository.findById(district.getState().getId());
				airports.setDistrict(district);
				airports.setState(state);

				String string1 = row.getCell(1).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				airports.setAirportName(string1);

				String string2 = row.getCell(3).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				airports.setAirportAddress(string2);

				String string3 = row.getCell(4).toString();
				byte[] u3 = string3.getBytes("UTF-8");
				string3 = new String(u3, "UTF-8");
				airports.setDescription(string3);

				airports.setStatus(Status.ACTIVE);

				airportsRepository.save(airports);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Airports file  uploaded Successfully!", HttpStatus.OK);
	}

	@ApiOperation(value = "Post Airports ")
	@RequestMapping(value = "addAirports", method = RequestMethod.POST)
	public ResponseEntity<Object> postAirports(@RequestParam String airportName, @RequestParam String district,
			@RequestParam String description, @RequestParam String airportAddress) {
		Airports airports = airportsService.postAirports(airportName, district, description, airportAddress);
		return new ResponseEntity<Object>(airports.getAirportName() + " posted successfully", HttpStatus.OK);

	}

//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "token", required = true, dataType = "string", paramType = "header") })

	@ApiOperation(value = "get Airports")
	@RequestMapping(value = "getAirport/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAirport(@PathVariable String state, @RequestHeader Long LoginId) {
		List<AirportsResponseDto> airportResponseDto = airportsService.getAirports(state);
		return new ResponseEntity<Object>(airportResponseDto, HttpStatus.OK);

	}

}
