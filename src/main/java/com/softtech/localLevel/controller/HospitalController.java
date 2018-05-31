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

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.HospitalRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.HospitalResponseDto;
import com.softtech.localLevel.service.HospitalService;
import com.softtech.localLevel.util.Status;

import io.swagger.annotations.ApiOperation;
/**
 * <<This is the controller for Hospital>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 March 2018
*/
@RestController
@RequestMapping("rest/hospital")
public class HospitalController {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private HospitalService hospitalService;

	@ApiOperation(value = "Upload an excel file for Natural Resources-Mountains")
	@RequestMapping(value = "/uploadHospital/hospitals", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForHospital(@RequestParam("Hospitals") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

		Hospital nr = hospitalRepository.findByHospitalAndStatusNot(" Bir Hospital", Status.DELETED);
		if (nr != null) {
			throw new AlreadyExistException("Hospital Files Already uploaded!");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Hospital hospital = new Hospital();
				String string0 = row.getCell(3).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				District district = districtRepository.findByDistrict(string0);
				State state = stateRepository.findById(district.getState().getId());
				hospital.setDistrict(district);
				hospital.setState(state);

				String string1 = row.getCell(0).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				hospital.setHospital(string1);

				String string2 = row.getCell(2).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				hospital.setAddress(string2);

				String string3 = row.getCell(4).toString();
				byte[] u3 = string3.getBytes("UTF-8");
				string3 = new String(u3, "UTF-8");
				hospital.setDescription(string3);

				String string4 = row.getCell(1).toString();
				byte[] u4 = string4.getBytes("UTF-8");
				string4 = new String(u4, "UTF-8");
				hospital.setContactNumber(string4);

				hospital.setStatus(Status.ACTIVE);

				hospitalRepository.save(hospital);
			}

			catch (UnsupportedEncodingException e) {
			}

		}
		return new ResponseEntity<Object>("Hospital File uploaded successfully!", HttpStatus.OK);
	}

	@ApiOperation(value = "Post hospital")
	@RequestMapping(value = "addHospital", method = RequestMethod.POST)
	public ResponseEntity<Object> posthospital(@RequestParam String hospitalName, @RequestParam String district,
			@RequestParam String address, @RequestParam String hospitalContactNumber,
			@RequestParam String description) {
		Hospital infra = hospitalService.postHospital(hospitalName, district, hospitalContactNumber, description,
				address);
		return new ResponseEntity<Object>(infra.getHospital() + " posted Successfully", HttpStatus.OK);

	}

	@ApiOperation(value = "get Hospital")
	@RequestMapping(value = "getHospital/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getHospital(@PathVariable String state) {

		List<HospitalResponseDto> hospitalResponseDtos = hospitalService.getHospitalDetail(state);
		return new ResponseEntity<Object>(hospitalResponseDtos, HttpStatus.OK);

	}
}
