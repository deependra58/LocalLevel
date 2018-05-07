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
import com.softtech.localLevel.model.Hydropower;
import com.softtech.localLevel.model.Industry;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.IndustryRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.IndustriesResponseDto;
import com.softtech.localLevel.service.IndustryService;
import com.softtech.localLevel.util.Status;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/industry")
public class IndustryController {
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private IndustryService industryService;
	
	@Autowired
	private IndustryRepository industryRepository;
	
	@ApiOperation(value = "Upload an excel file for Infrastructure-Industry")
	@RequestMapping(value = "/uploadIndustry/Industry", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForIndustry(@RequestParam("Industry") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

		Industry nr = industryRepository.findByIndustryAndStatusNot(" Hetauda Cement Factory", Status.DELETED);
		if (nr != null) {
			throw new AlreadyExistException("Industry Files Already uploaded!");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Industry industry = new Industry();
				String string0 = row.getCell(2).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				District district = districtRepository.findByDistrict(string0);
				State state = stateRepository.findById(district.getState().getId());
				industry.setDistrict(district);
				industry.setState(state);

				String string1 = row.getCell(1).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				industry.setIndustry(string1);

				String string2 = row.getCell(3).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				industry.setAddress(string2);

				String string3 = row.getCell(4).toString();
				byte[] u3 = string3.getBytes("UTF-8");
				string3 = new String(u3, "UTF-8");
				industry.setDescription(string3);
				
				
				industry.setStatus(Status.ACTIVE);

				industryRepository.save(industry);
			}

			catch (UnsupportedEncodingException e) {
			}

		}
		return new ResponseEntity<Object>("Hydropower File uploaded successfully!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Post industries")
	@RequestMapping(value = "addIndustries", method = RequestMethod.POST)
	public ResponseEntity<Object> postIndustry(@RequestParam String industryName, @RequestParam String district,@RequestParam String address, @RequestParam String description) {
		Industry infra = industryService.postIndustry(industryName, district,description,address);

		return new ResponseEntity<Object>(infra.getIndustry() + " posted Successfully", HttpStatus.OK);

	}
	
	@ApiOperation(value="get industries")
	@RequestMapping(value="getIndustries/{state:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getIndustries(@PathVariable String state){
		
		List<IndustriesResponseDto> industriesResponseDto=industryService.getIndustries(state);
		return new ResponseEntity<Object>(industriesResponseDto, HttpStatus.OK);
		
	}

}
