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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Hospital;
import com.softtech.localLevel.model.Hydropower;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.HydropowerRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.HydropowerResponseDto;
import com.softtech.localLevel.service.HydropowerService;
import com.softtech.localLevel.util.Status;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/hydropower")
public class HydropowerController {
	
	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private HydropowerRepository hydropowerRepository;
	
	@Autowired
	private HydropowerService hydropowerService;
	
	@ApiOperation(value = "Upload an excel file for Infrastructure-Hydropower")
	@RequestMapping(value = "/uploadHydropower/Hydropower", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForHydropower(@RequestParam("Hydropower") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

		Hydropower nr = hydropowerRepository.findByHydropowerAndStatusNot(" kaligandaki Hydropower", Status.DELETED);
		if (nr != null) {
			throw new AlreadyExistException("Hydropower Files Already uploaded!");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Hydropower hydropower = new Hydropower();
				String string0 = row.getCell(2).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				District district = districtRepository.findByDistrict(string0);
				State state = stateRepository.findById(district.getState().getId());
				hydropower.setDistrict(district);
				hydropower.setState(state);

				String string1 = row.getCell(1).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				hydropower.setHydropower(string1);

				String string2 = row.getCell(3).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				hydropower.setCapacity(string2);

				String string3 = row.getCell(4).toString();
				byte[] u3 = string3.getBytes("UTF-8");
				string3 = new String(u3, "UTF-8");
				hydropower.setDescription(string3);
				
				String string4 = row.getCell(5).toString();
				byte[] u4 = string4.getBytes("UTF-8");
				string4 = new String(u4, "UTF-8");
				hydropower.setAddress(string4);

				hydropower.setStatus(Status.ACTIVE);

				hydropowerRepository.save(hydropower);
			}

			catch (UnsupportedEncodingException e) {
			}

		}
		return new ResponseEntity<Object>("Hydropower File uploaded successfully!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Post hydropower")
	@RequestMapping(value = "addHydropower", method = RequestMethod.POST)
	public ResponseEntity<Object> postHydropower(@RequestParam String hydropowerName, @RequestParam String district,@RequestParam String address,@RequestParam String description,@RequestParam String capacity) {
		Hydropower infra = hydropowerService.postHydropower(hydropowerName, district,capacity,description,address);
		System.out.println("Hydropower name:" + infra.getHydropower());
		return new ResponseEntity<Object>(infra.getHydropower() + " posted Successfully", HttpStatus.OK);

	}
	
	@ApiOperation(value="get Hydropower")
	@RequestMapping(value="getHydropower/{state:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getHydropower(@PathVariable String state){
		
		List<HydropowerResponseDto> hydropowerResponseDtos=hydropowerService.getHydropower(state);
		return new ResponseEntity<Object>(hydropowerResponseDtos, HttpStatus.OK);
		
	}

}
