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
import com.softtech.localLevel.model.Lakes;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.LakeRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.LakesResponseDto;
import com.softtech.localLevel.service.LakesService;
import com.softtech.localLevel.util.Status;

import io.swagger.annotations.ApiOperation;
/**
 * <<This is the controller for Lake>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 March 2018
*/
@RestController
@RequestMapping("rest/lakes")
public class LakeController {
	
	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private LakeRepository lakeRepository;
	
	@Autowired
	private LakesService lakeService;
	
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

		Lakes nr = lakeRepository.findBylakes("rara");
		if (nr != null) {
			throw new AlreadyExistException("Rara Files Already uploaded!");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Lakes naturalResources = new Lakes();
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
				naturalResources.setLakes(string1);

				naturalResources.setStatus(Status.ACTIVE);

				lakeRepository.save(naturalResources);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Lakes file  uploaded Successfully!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Post lakes Manually", notes = "Posting lakes")
	@RequestMapping(value = "addLakes", method = RequestMethod.POST)
	public ResponseEntity<Object> postlakes(@RequestParam String lake_name, @RequestHeader String district, @RequestParam String description) {
		Lakes ns = lakeService.postLake(lake_name, district,description);
		return new ResponseEntity<Object>(ns.getLakes() + " Lake added successuflly", HttpStatus.OK);

	}
	
	@ApiOperation(value = "Get Lakes")
	@RequestMapping(value = "getLakes/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getLakes(@PathVariable String state) {

		List<LakesResponseDto> lakesResponseDtoList = lakeService.getLakes(state);
		return new ResponseEntity<Object>(lakesResponseDtoList, HttpStatus.OK);

	}

	

}
