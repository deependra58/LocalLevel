package com.softtech.localLevel.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

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
import com.softtech.localLevel.model.Mountains;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.MountainsRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.MountainsResponseDto;
import com.softtech.localLevel.service.MountainService;
import com.softtech.localLevel.util.Status;

import io.swagger.annotations.ApiOperation;
/**
 * <<This is the controller for Mountain>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 March 2018
*/
@RestController
@RequestMapping("rest/mountains")
public class MountainController {
	
	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private MountainsRepository mountainsRepository;
	
	@Autowired
	private MountainService mountainService;

	

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

		Mountains nr = mountainsRepository.findByMountain("Mount Everest");
		if (nr != null) {
			throw new AlreadyExistException("Mountains Files Already uploaded!");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Mountains mountains = new Mountains();
				String string0 = row.getCell(3).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				District district = districtRepository.findByDistrict(string0);
				State state = stateRepository.findById(district.getState().getId());
				mountains.setDistrict(district);
				mountains.setState(state);
				

				String string1 = row.getCell(1).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				mountains.setMountain(string1);

				String string2 = row.getCell(2).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				mountains.setHeight(string2);

				mountains.setStatus(Status.ACTIVE);

				mountainsRepository.save(mountains);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Mountains file  uploaded Successfully!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Post the Mountain Manually", notes = "Posting mountain")
	@RequestMapping(value = "addMountain", method = RequestMethod.POST)
	public ResponseEntity<Object> postMountains(@RequestParam String mountain_name, @RequestParam String mountainHeight,
			@RequestHeader String district, @RequestParam String description) {
		Mountains ns = mountainService.postMountain(mountain_name, mountainHeight, district,description);
		return new ResponseEntity<Object>(ns.getMountain() + " Mountain added successuflly", HttpStatus.OK);

	}
	
	@ApiOperation(value = "Get Mountains")
	@RequestMapping(value = "getMountains/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getMountains(@PathVariable String state) {

		List<MountainsResponseDto> mountainsResponseDtoList = mountainService.getMountain(state);
		System.out.println("Mountains:"+ mountainsResponseDtoList);
		return new ResponseEntity<Object>(mountainsResponseDtoList, HttpStatus.OK);

	}

}
