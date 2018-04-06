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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.NaturalResources;
import com.softtech.localLevel.model.RuralMunicipality;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.NaturalResourcesRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.LakesResponseDto;
import com.softtech.localLevel.response.MountainsResponseDto;
import com.softtech.localLevel.response.ProtectedAreaResponseDto;
import com.softtech.localLevel.response.RiversResponseDto;
import com.softtech.localLevel.response.WaterfallResponseDto;
import com.softtech.localLevel.service.NaturalResourcesService;
import com.softtech.localLevel.util.LocalLevelType;
import com.softtech.localLevel.util.Status;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/naturalResources")

public class NaturalResourcesController {
	@Autowired
	private NaturalResourcesService naturalResourcesService;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private NaturalResourcesRepository naturalResourcesRepository;

	@ApiOperation(value = "Upload an excel file for Natural Resources-Mountains")
	@RequestMapping(value = "/uploadNaturalResources/mountains", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForMountain(@RequestParam("mountains") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

		NaturalResources nr = naturalResourcesRepository.findByMountain("Mount Everest");
		if (nr != null) {
			throw new AlreadyExistException("Mountains Files Already uploaded!");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				NaturalResources naturalResources = new NaturalResources();
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
				naturalResources.setMountain(string1);

				String string2 = row.getCell(2).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				naturalResources.setMountainHeight(string2);

				naturalResources.setStatus(Status.ACTIVE);

				naturalResourcesRepository.save(naturalResources);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Mountains file  uploaded Successfully!", HttpStatus.OK);
	}

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

		NaturalResources nr = naturalResourcesRepository.findByRiver("Karnali");
		if (nr != null) {
			throw new AlreadyExistException("River Files Already uploaded!");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				NaturalResources naturalResources = new NaturalResources();
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

				naturalResources.setStatus(Status.ACTIVE);

				naturalResourcesRepository.save(naturalResources);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Rivers file  uploaded Successfully!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Upload an excel file for Natural Resources-Lakes")
	@RequestMapping(value = "/uploadNaturalResources/lakes", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForLakes(@RequestParam("lakes") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

		NaturalResources nr = naturalResourcesRepository.findBylake("rara");
		if (nr != null) {
			throw new AlreadyExistException("Rara Files Already uploaded!");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				NaturalResources naturalResources = new NaturalResources();
				String string0 = row.getCell(2).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				District district = districtRepository.findByDistrict(string0);
				State state = stateRepository.findById(district.getState().getId());
				naturalResources.setDistrict(district);
				naturalResources.setState(state);
				


				String string1 = row.getCell(1).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				naturalResources.setLake(string1);

				naturalResources.setStatus(Status.ACTIVE);

				naturalResourcesRepository.save(naturalResources);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Lakes file  uploaded Successfully!", HttpStatus.OK);
	}

	@ApiOperation(value = "Post the Mountain Manually", notes = "Posting mountain")
	@RequestMapping(value = "addMountain", method = RequestMethod.POST)
	public ResponseEntity<Object> postMountains(@RequestParam String mountain_name, @RequestParam String mountainHeight,
			@RequestHeader String district) {
		NaturalResources ns = naturalResourcesService.postMountain(mountain_name, mountainHeight, district);
		return new ResponseEntity<Object>(ns.getMountain() + " Mountain added successuflly", HttpStatus.OK);

	}

	@ApiOperation(value = "Post rivers Manually", notes = "Posting rivers")
	@RequestMapping(value = "addRiver", method = RequestMethod.POST)
	public ResponseEntity<Object> postRiver(@RequestParam String river_name, @RequestHeader String district) {
		NaturalResources ns = naturalResourcesService.postRiver(river_name, district);
		return new ResponseEntity<Object>(ns.getRiver() + " River added successuflly", HttpStatus.OK);

	}

	@ApiOperation(value = "Post lakes Manually", notes = "Posting lakes")
	@RequestMapping(value = "addLakes", method = RequestMethod.POST)
	public ResponseEntity<Object> postlakes(@RequestParam String lake_name, @RequestHeader String district) {
		NaturalResources ns = naturalResourcesService.postLake(lake_name, district);
		return new ResponseEntity<Object>(ns.getLake() + " Lake added successuflly", HttpStatus.OK);

	}

	@ApiOperation(value = "Post waterfalls Manually", notes = "Posting waterfall")
	@RequestMapping(value = "addWaterfall", method = RequestMethod.POST)
	public ResponseEntity<Object> postwaterfall(@RequestParam String waterfall_name, @RequestHeader String district) {
		NaturalResources ns = naturalResourcesService.postWaterfall(waterfall_name, district);
		return new ResponseEntity<Object>(ns.getWaterfalls() + " waterfall added successuflly", HttpStatus.OK);

	}

	@ApiOperation(value = "Post protectedAreas Manually", notes = "Posting waterfall")
	@RequestMapping(value = "addProtectedAreas", method = RequestMethod.POST)
	public ResponseEntity<Object> postProtectedAreas(@RequestParam String protectedArea_name,
			@RequestHeader String district) {
		NaturalResources ns = naturalResourcesService.postProtectedArea(protectedArea_name, district);
		return new ResponseEntity<Object>(ns.getProtectedAreas() + " protected Area added successuflly", HttpStatus.OK);

	}

	@ApiOperation(value = "Get Mountains")
	@RequestMapping(value = "getMountains/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getMountains(@PathVariable String state) {

		List<MountainsResponseDto> mountainsResponseDtoList = naturalResourcesService.getMountain(state);
		return new ResponseEntity<Object>(mountainsResponseDtoList, HttpStatus.OK);

	}

	@ApiOperation(value = "Get Rivers")
	@RequestMapping(value = "getRivers/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getRivers(@PathVariable String state) {

		List<RiversResponseDto> riversResponseDtoList = naturalResourcesService.getRivers(state);
		return new ResponseEntity<Object>(riversResponseDtoList, HttpStatus.OK);

	}

	@ApiOperation(value = "Get Lakes")
	@RequestMapping(value = "getLakes/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getLakes(@PathVariable String state) {

		List<LakesResponseDto> lakesResponseDtoList = naturalResourcesService.getLakes(state);
		return new ResponseEntity<Object>(lakesResponseDtoList, HttpStatus.OK);

	}

	@ApiOperation(value = "Get waterfall")
	@RequestMapping(value = "getWaterfall/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getWaterfall(@PathVariable String state) {

		List<WaterfallResponseDto> waterfallResponseDtoList = naturalResourcesService.getWaterfall(state);
		return new ResponseEntity<Object>(waterfallResponseDtoList, HttpStatus.OK);

	}

	@ApiOperation(value = "Get protected Areas")
	@RequestMapping(value = "getProtectedArea/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getProtectedArea(@PathVariable String state) {

		List<ProtectedAreaResponseDto> protectedAreaResponseDtoList = naturalResourcesService.getProtectedArea(state);
		return new ResponseEntity<Object>(protectedAreaResponseDtoList, HttpStatus.OK);

	}

}
