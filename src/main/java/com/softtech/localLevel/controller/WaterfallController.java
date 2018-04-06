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
import com.softtech.localLevel.model.NaturalResources;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.model.Waterfall;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.repository.WaterfallRepository;
import com.softtech.localLevel.response.WaterfallResponseDto;
import com.softtech.localLevel.service.WaterfallService;
import com.softtech.localLevel.util.Status;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/waterfall")
public class WaterfallController {
	
	@Autowired
	private WaterfallRepository waterfallRepsoitory;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private WaterfallService waterfallService;
	
	@ApiOperation(value = "Upload an excel file for Natural Resources-waterfalls")
	@RequestMapping(value = "/uploadNaturalResources/waterfalls", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetFor(@RequestParam("lakes") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

		Waterfall nr = waterfallRepsoitory.findByWaterfall("rara");
		if (nr != null) {
			throw new AlreadyExistException("Waterfall Files Already uploaded!");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Waterfall naturalResources = new Waterfall();
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
				naturalResources.setWaterfall(string1);
				
				String string2 = row.getCell(1).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				naturalResources.setWaterfall(string2);
				

				naturalResources.setStatus(Status.ACTIVE);

				waterfallRepsoitory.save(naturalResources);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Waterfall file  uploaded Successfully!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Post waterfalls Manually", notes = "Posting waterfall")
	@RequestMapping(value = "addWaterfall", method = RequestMethod.POST)
	public ResponseEntity<Object> postwaterfall(@RequestParam String waterfall_name, @RequestHeader String district, @RequestParam String description) {
		Waterfall ns = waterfallService.postWaterfall(waterfall_name, district, description);
		return new ResponseEntity<Object>(ns.getWaterfall() + " waterfall added successuflly", HttpStatus.OK);

	}
	
	@ApiOperation(value = "Get waterfall")
	@RequestMapping(value = "getWaterfall/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getWaterfall(@PathVariable String state) {

		List<WaterfallResponseDto> waterfallResponseDtoList = waterfallService.getWaterfall(state);
		return new ResponseEntity<Object>(waterfallResponseDtoList, HttpStatus.OK);

	}

}
