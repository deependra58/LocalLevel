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

import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Metropolitan;
import com.softtech.localLevel.model.Municipality;
import com.softtech.localLevel.model.RuralMunicipality;
import com.softtech.localLevel.model.SubMetropolitan;
import com.softtech.localLevel.model.Vdc;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.MetropolitanRepository;
import com.softtech.localLevel.repository.MunicipalityRepository;
import com.softtech.localLevel.repository.RuralMunicipalityRepository;
import com.softtech.localLevel.repository.SubMetropolitanRepository;
import com.softtech.localLevel.repository.VdcRepository;
import com.softtech.localLevel.response.OldVdcListResponse;
import com.softtech.localLevel.response.VdcResponse;
import com.softtech.localLevel.service.VdcService;
import com.softtech.localLevel.util.Status;

import io.swagger.annotations.ApiOperation;
/**
 * <<This is the controller for Vdc>>
 * @Author Deependra
 * @Version 1.0.0
 * @Since , 2 April 2018
*/
@RestController
@RequestMapping("/rest/vdcs")
public class VdcController {

	@Autowired
	private VdcService vdcService;
	
	@Autowired
	private VdcRepository vdcRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RuralMunicipalityRepository ruralMunicipalityRepository;
	
	@Autowired
	private MunicipalityRepository municipalityRepository;
	
	@Autowired
	private SubMetropolitanRepository subMetropolitanRespository;
	
	@Autowired
	private MetropolitanRepository metropolitanRepository;
	
	@ApiOperation(value = "Upload an excel file containing old vdc of all rural municipality")
	@RequestMapping(value = "/uploadOldVdc/ruralMunicipality", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForRuralMunicipality(@RequestParam("RuralMunicipality") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		@SuppressWarnings("rawtypes")
		Iterator rows = sheet.rowIterator();

//		Vdc nr =vdcRepository .findByRiver("Karnali");
//		if (nr != null) {
//			throw new AlreadyExistException("River Files Already uploaded!");
//		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Vdc vdc = new Vdc();
				String string0 = row.getCell(0).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				District district = districtRepository.findByDistrict(string0);
				// naturalResources.setDistrict(district);
				vdc.setDistrict(district);

				String string1 = row.getCell(1).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				RuralMunicipality rMunicipality=ruralMunicipalityRepository.findByRuralMunicipal(string1);
				Long id=rMunicipality.getId();
				System.out.println("Id====="+id);
				vdc.setRuralMunicipalityId(rMunicipality.getId());
				Long i=(long) 0;
				vdc.setMunicipalityId(i);
				vdc.setSubMetropolitanId(i);
				vdc.setMetropolitanId(i);

				String string2 = row.getCell(2).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				vdc.setVdc(string2);

				vdc.setStatus(Status.ACTIVE);

				vdcRepository.save(vdc);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("RuralMunicipality file  uploaded Successfully!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Upload an excel file containing old vdc of all municipality")
	@RequestMapping(value = "/uploadOldVdc/municipality", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForMunicipality(@RequestParam("municipalityFile") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

//		Vdc nr =vdcRepository .findByRiver("Karnali");
//		if (nr != null) {
//			throw new AlreadyExistException("River Files Already uploaded!");
//		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Vdc vdc = new Vdc();
				String string0 = row.getCell(0).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				District district = districtRepository.findByDistrict(string0);
				// naturalResources.setDistrict(district);
				vdc.setDistrict(district);

				String string1 = row.getCell(1).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				Municipality municipality=municipalityRepository.findByMunicipal(string1);
				vdc.setMunicipalityId(municipality.getId());
				Long i=(long) 0;
				vdc.setRuralMunicipalityId(i);
				vdc.setSubMetropolitanId(i);
				vdc.setMetropolitanId(i);

				String string2 = row.getCell(2).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				vdc.setVdc(string2);

				vdc.setStatus(Status.ACTIVE);

				vdcRepository.save(vdc);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Municipality file  uploaded Successfully!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Upload an excel file containing old vdc of all subMetropolitan")
	@RequestMapping(value = "/uploadOldVdc/subMetropolitan", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForSubMetropolitan(@RequestParam("SubMetropolitanFile") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

//		Vdc nr =vdcRepository .findByRiver("Karnali");
//		if (nr != null) {
//			throw new AlreadyExistException("River Files Already uploaded!");
//		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Vdc vdc = new Vdc();
				String string0 = row.getCell(0).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				District district = districtRepository.findByDistrict(string0);
				// naturalResources.setDistrict(district);
				vdc.setDistrict(district);

				String string1 = row.getCell(1).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				SubMetropolitan subMetropolitan=subMetropolitanRespository.findBySubMetropolitan(string1);
				vdc.setSubMetropolitanId(subMetropolitan.getId());
				Long i=(long) 0;
				vdc.setMunicipalityId(i);
				vdc.setRuralMunicipalityId(i);
				vdc.setMetropolitanId(i);

				String string2 = row.getCell(2).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				vdc.setVdc(string2);

				vdc.setStatus(Status.ACTIVE);

				vdcRepository.save(vdc);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("SubMetropolitan file  uploaded Successfully!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Upload an excel file containing old vdc of all Metropolitan")
	@RequestMapping(value = "/uploadOldVdc/metropolitan", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheetForMetropolitan(@RequestParam("MetropolitanFile") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

//		Vdc nr =vdcRepository .findByRiver("Karnali");
//		if (nr != null) {
//			throw new AlreadyExistException("River Files Already uploaded!");
//		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				Vdc vdc = new Vdc();
				String string0 = row.getCell(0).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				District district = districtRepository.findByDistrict(string0);
				// naturalResources.setDistrict(district);
				vdc.setDistrict(district);

				String string1 = row.getCell(1).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				Metropolitan metropolitan=metropolitanRepository.findByMetropolitan(string1);
				vdc.setMetropolitanId(metropolitan.getId());
				Long i=(long) 0;
				vdc.setMunicipalityId(i);
				vdc.setSubMetropolitanId(i);
				vdc.setRuralMunicipalityId(i);

				String string2 = row.getCell(2).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				vdc.setVdc(string2);

				vdc.setStatus(Status.ACTIVE);

				vdcRepository.save(vdc);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("Metropolitan file  uploaded Successfully!", HttpStatus.OK);
	}
	

	@ApiOperation(value = "Post old vdc details manually")
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<Object> postVdcDetails( @RequestParam (required=true) String vdc, @RequestParam (required=true)
	 String district, @RequestParam(required=false) String ruralMunicipality,
	 @RequestParam(required=false) String municipality,
	 @RequestParam(required=false) String
	 subMetropolitan,@RequestParam(required=false) String metropolitan) {
		
		 vdcService.postVdcDetails(vdc,district,ruralMunicipality,municipality,subMetropolitan,metropolitan);
		return new ResponseEntity<Object>("Posted successfully", HttpStatus.OK);

	}
	
	@ApiOperation(value="Edit old vdc details")
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Object> putVdcDetails(@RequestHeader Long vdcId,@RequestParam (required=true) String vdc, @RequestParam (required=true)
	 String district, @RequestParam(required=false) String ruralMunicipality,
	 @RequestParam(required=false) String municipality,
	 @RequestParam(required=false) String
	 subMetropolitan,@RequestParam(required=false) String metropolitan){
		
		 vdcService.editVdcDetails(vdcId,vdc,district,ruralMunicipality,municipality,subMetropolitan,metropolitan);
		 return new ResponseEntity<Object>("Edited successfully", HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Delete old vdc")
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteVdc(@RequestParam (required=false) Long vdcId, @RequestParam (required=false) String vdc){
		vdcService.deleteVdc(vdcId,vdc);
		return new ResponseEntity<Object> ("Deleted Successfully", HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Get old vdc list from district")
	@RequestMapping(value="district/{district:.+}",method=RequestMethod.GET) 
	public ResponseEntity<Object> getNewVdc(@PathVariable String district){
		List<OldVdcListResponse> oldVdcListResponses=vdcService.getOldVdcList(district);
		return new ResponseEntity<Object>( oldVdcListResponses,HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Get new vdc from old vdc")
	@RequestMapping(value="oldVdc/{oldVdc:.+}",method=RequestMethod.GET)
	public ResponseEntity<Object> getnewVdcs(@PathVariable  String oldVdc){
		List<VdcResponse> vdcResponse=vdcService.getNewVdcs(oldVdc);
		return new ResponseEntity<Object>(vdcResponse,HttpStatus.OK);
		
	}
	
	
	@ApiOperation(value="Get old vdc list from new Vdc")
	@RequestMapping(value="OldVdcList/{newVdcs:.+}", method=RequestMethod.GET)
	public ResponseEntity<Object> getOldVdcs(@PathVariable String newVdcs){
		List<OldVdcListResponse> oldVdcListResponses=vdcService.getOldVdcsFromNewVdc(newVdcs);
		return new ResponseEntity<Object>(oldVdcListResponses, HttpStatus.OK);
		
	}
	
}

