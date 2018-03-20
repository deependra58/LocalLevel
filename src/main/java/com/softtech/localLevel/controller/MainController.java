package com.softtech.localLevel.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.NewVdc;
import com.softtech.localLevel.model.OldVdc;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.NewVdcRepository;
import com.softtech.localLevel.repository.NewWardRepository;
import com.softtech.localLevel.repository.OldVdcRepository;
import com.softtech.localLevel.repository.OldWardRepository;
import com.softtech.localLevel.repository.StateRepository;

import io.swagger.annotations.ApiOperation;
@RequestMapping("/rest")
@RestController
public class MainController {

	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private NewVdcRepository newVdcRepository;
	@Autowired
	private OldVdcRepository oldVdcRepository;
	@Autowired
	private NewWardRepository newWardRepository;
	@Autowired
	private OldWardRepository oldWardRepository;
/*	@Autowired
	StateDistrictRepository stateDistrictRepository;*/

	@SuppressWarnings({ "null", "unused" })
	@ApiOperation(value="Upload an excel file ")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheet(@RequestParam("local") MultipartFile multipartFile) throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

		int k = 0, counter = 2;
		String temp=null;
		String[] temp0 = new String[35083];
		temp0[0] = null;
		String[] temp1 = new String[35083];
		temp1[0] = null;
		String[] temp2 = new String[35083];
		temp2[0] = null;
		String[] temp3 = new String[35083];
		temp3[0] = null;
		String[] temp4 = new String[35083];
		temp4[0] = null;
		String[] temp5 = new String[35083];
		temp5[0] = null;

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {

				State state = new State();
				String string0 = row.getCell(0).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				for (int j = 0; j < k + 1; j++) {
					if (!string0.equalsIgnoreCase(temp0[j])) {
						counter = 0;
					} else {
						counter = 1;
						break;
					}

				}
				temp0[k + 1] = string0;
				if (counter == 0) {
					state.setState(string0);
					stateRepository.save(state);
				}
				
				
				
			
				/*NewVdc newVdc = new NewVdc();
				String string2 = row.getCell(2).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				if(!temp.equalsIgnoreCase(string2)) {
					newVdc.setNewVdc(string2);
					newVdcRepository.save(newVdc);
					
					
				}
				
				*/
				
				
				
				

				District district = new District();
				String string1 = row.getCell(1).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				for (int j = 0; j < k + 1; j++) {
					if (!string1.equalsIgnoreCase(temp1[j])) {
						counter = 0;
					} else {
						counter = 1;
						break;
					}

				}
				temp1[k + 1] = string1;
				if (counter == 0) {
					district.setDistrict(string1);
					districtRepository.save(district);
					// System.out.println(district1.toString());
				}

				NewVdc newVdc = new NewVdc();
				String string2 = row.getCell(2).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				for (int j = 0; j < k + 1; j++) {
					if (!string2.equalsIgnoreCase(temp2[j])) {
						counter = 0;
					} else {
						counter = 1;
						break;
					}

				}
				temp2[k + 1] = string2;
				if (counter == 0) {
					newVdc.setNewVdc(string2);
					newVdcRepository.save(newVdc);
				}

				/*NewWard newWard = new NewWard();
				String string3 = row.getCell(4).toString();
				byte[] u3 = string3.getBytes("UTF-8");
				string3 = new String(u3, "UTF-8");
				String[] a = string3.split(Pattern.quote("."));
				for (int j = 0; j < k + 1; j++) {
					if (!string3.equalsIgnoreCase(temp3[j])) {
						counter = 0;
					} else {
						counter = 1;
						break;
					}

				}
				temp3[k + 1] = string3;
				if (counter == 0) {
					newWard.setNewWard(Long.parseLong(a[0]));
					newWardRepository.save(newWard);
				}*/

				OldVdc oldVdc = new OldVdc();
				String string4 = row.getCell(4).toString();
				byte[] u4 = string4.getBytes("UTF-8");
				string4 = new String(u4, "UTF-8");
				for (int j = 0; j < k + 1; j++) {
					if (!string4.equalsIgnoreCase(temp4[j])) {
						counter = 0;
					} else {
						counter = 1;
						break;
					}

				}
				temp4[k + 1] = string4;
				if (counter == 0) {
					oldVdc.setOldVdc(string4);
					oldVdcRepository.save(oldVdc);
				}

				/*OldWard oldWard = new OldWard();
				String string5 = row.getCell(6).toString();
				byte[] u5 = string5.getBytes("UTF-8");
				string5 = new String(u5, "UTF-8");
				String[] b = string5.split(Pattern.quote("."));
				for (int j = 0; j < k + 1; j++) {
					if (!string5.equalsIgnoreCase(temp5[j])) {
						counter = 0;
					} else {
						counter = 1;
						break;
					}

				}
				temp5[k + 1] = string5;
				if (counter == 0) {
					oldWard.setOldWard(Long.parseLong(b[0]));
					oldWardRepository.save(oldWard);
				}*/


			}

			catch (UnsupportedEncodingException e) {
			}
			k++;
		
			
			}

		

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
