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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.request.MinistryCreationDto;
import com.softech.localLevel.request.MinistryEditRequest;
import com.softtech.localLevel.dto.MinistryDetailsDto;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Ministry;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.ministryRepository;
import com.softtech.localLevel.response.MinistryResponseDto;
import com.softtech.localLevel.service.MininstryService;
import com.softtech.localLevel.util.GovType;
import com.softtech.localLevel.util.Status;

import io.swagger.annotations.ApiOperation;
/**
 * <<This is the controller for Ministry>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 March 2018
*/
@RestController
@RequestMapping(value = "rest/ministry")
public class MinistryController {

	@Autowired
	private MininstryService ministryService;
	
	@Autowired
	private ministryRepository ministryRepository;
	
	
	@ApiOperation(value = "Upload an excel file for Central Ministry")
	@RequestMapping(value = "/uploadCentralMinistry", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForCentralMinistry(@RequestParam("CnetralMinistry") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

//		ministry nr = ministryRepository.findByAirportNameAndStatusNot("Tribhuvan International Airport",
//				Status.DELETED);
//		if (nr != null) {
//			throw new AlreadyExistException("Airport Files Already uploaded!");
//		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Ministry ministry = new Ministry();
//				String string0 = row.getCell(2).toString();
//				byte[] u0 = string0.getBytes("UTF-8");
//				string0 = new String(u0, "UTF-8");
//				District district = districtRepository.findByDistrict(string0);
//				State state = stateRepository.findById(district.getState().getId());
//				ministry.setDistrict(district);
//				ministry.setState(state);

				String string1 = row.getCell(0).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				ministry.setMinistryName(string1);

				String string2 = row.getCell(1).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				ministry.setMinisterName(string2);

				String string3 = row.getCell(2).toString();
				byte[] u3 = string3.getBytes("UTF-8");
				string3 = new String(u3, "UTF-8");
				ministry.setStateMinister(string3);
				
				
				String string4 = row.getCell(3).toString();
				byte[] u4 = string4.getBytes("UTF-8");
				string4 = new String(u4, "UTF-8");
				ministry.setParty(string4);
				

				ministry.setStatus(Status.ACTIVE);
				ministry.setGovType(GovType.CENTRAL);

				ministryRepository.save(ministry);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("ministry file  uploaded Successfully!", HttpStatus.OK);
	}

	@ApiOperation(value = "Post Central Ministry detail", notes = " Post Central Ministry Detail")
	@RequestMapping(value = "ministries/central", method = RequestMethod.POST)
	public ResponseEntity<Object> postCentralMinistryInfo(@RequestBody MinistryCreationDto minsitryCreationDto) {

		Ministry ms = ministryService.createCentralMinistry(minsitryCreationDto);
		return new ResponseEntity<Object>("New Ministry added: ID=" + ms.getId(), HttpStatus.OK);
	}

	@ApiOperation(value = "Post Local Ministry detail", notes = " Post Local  Ministry Detail")
	@RequestMapping(value = "ministries/local", method = RequestMethod.POST)
	public ResponseEntity<Object> postLocalMinistryInfo(@RequestBody MinistryCreationDto minsitryCreationDto,
			@RequestHeader String stateName) {
		// System.out.println("success");
		Ministry ms = ministryService.createLocalMinistry(minsitryCreationDto, stateName);
		return new ResponseEntity<Object>("New Ministry added: ID=" + ms.getId(), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete Central Mininstry detail", notes = "Delete Central ministry Detail")
	@RequestMapping(value = "CentralMinistries/{ministryName:.+}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCentralMinistry(@PathVariable String ministryName) {
		ministryService.deleteMinistry(ministryName);
		return new ResponseEntity<Object>("Ministry Deleted", HttpStatus.OK);

	}
	

	@ApiOperation(value = "Delete Local Mininstry detail", notes = "Delete Local ministry Detail")
	@RequestMapping(value = "LocalMinistries/{ministryName:.+}/{stateName:.+}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteLocalMinistry(@PathVariable String ministryName, String stateName) {
		ministryService.deleteLocalMinistry(ministryName, stateName);
		return new ResponseEntity<Object>("Ministry Deleted", HttpStatus.OK);

	}

	@ApiOperation(value = "Show All Central Ministry Name", notes = "Display All Central ministry Name")
	@RequestMapping(value = "centralMinistries/showMinistry", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllCentralMinistry() {

		List<MinistryResponseDto> ministryList = ministryService.getCentralMinistries();
		return new ResponseEntity<Object>(ministryList, HttpStatus.OK);

	}

	@ApiOperation(value = "Show All Local Ministry Name", notes = "Display All Local ministry Name")
	@RequestMapping(value = "localMinistries/{stateName:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllLocalMinistry(@PathVariable String stateName) {

		List<MinistryResponseDto> ministryList = ministryService.getLocalMinistries(stateName);
		return new ResponseEntity<Object>(ministryList, HttpStatus.OK);

	}

	@ApiOperation(value = "Edit central Ministry information", notes = "Edit central Ministry Information")
	@RequestMapping(value = "centralMinistries/editMinistry", method = RequestMethod.PUT)
	public ResponseEntity<Object> editCentralMinistry(@RequestBody MinistryEditRequest ministryEditRequest,
			@RequestHeader String ministryName) {

		Ministry ministry = ministryService.editCentralMinistry(ministryEditRequest, ministryName);
		return new ResponseEntity<Object>(ministry, HttpStatus.OK);

	}

	@ApiOperation(value = "Edit local Ministry information", notes = "Edit local Ministry Information")
	@RequestMapping(value = "localMinistries/editMinistry", method = RequestMethod.PUT)
	public ResponseEntity<Object> editLocalMinistry(@RequestBody MinistryEditRequest ministryEditRequest,
			@RequestHeader String ministryName, @RequestHeader String state) {

		Ministry ministry = ministryService.editLocalMinistry(ministryEditRequest, ministryName, state);
		System.out.println(ministry.toString());
		return new ResponseEntity<Object>(ministry.getId(), HttpStatus.OK);

	}
	
	@ApiOperation(value="Show central Ministry Details", notes="Showing All central level government details")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> showCentralDetails(){
		List<MinistryDetailsDto> ministryDetailDto=ministryService.showCentralDetails();
		return new ResponseEntity<Object>(ministryDetailDto,HttpStatus.OK);
	}
	
	@ApiOperation(value="Show Local Ministry Details", notes="Showing Local level government details")
	@RequestMapping(value="centralMinistries/{ministryName:.+}/{stateName:.+}",method=RequestMethod.GET)
	public ResponseEntity<Object> showLocalDetails(@PathVariable String ministryName, @RequestHeader String stateName){
		MinistryDetailsDto ministryDetailDto=ministryService.showLocalDetails(ministryName, stateName);
		return new ResponseEntity<Object>(ministryDetailDto,HttpStatus.OK);
	}
	
}
