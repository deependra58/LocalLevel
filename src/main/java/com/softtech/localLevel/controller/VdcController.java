package com.softtech.localLevel.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.regex.Pattern;

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
import com.softtech.localLevel.model.Vdc;
import com.softtech.localLevel.repository.VdcRepository;

import io.swagger.annotations.ApiOperation;
@RequestMapping("/rest")
@RestController
public class VdcController {


	@Autowired 
	private VdcRepository vdcRepository;
	
	@ApiOperation(value="Upload an excel file ")
	@RequestMapping(value = "/uploads", method = RequestMethod.POST)
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

				Vdc vdc = new Vdc();
				String string0 = row.getCell(3).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				vdc.setNewVdcName(string0);
				
				String string1 = row.getCell(4).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				vdc.setChairmen(string1);
				
				String string2 = row.getCell(5).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				vdc.setViceChairmen(string2);
				
				
				
				
				
				
				
				
				String string3 = row.getCell(6).toString();
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
					vdc.setDistrictId(Long.parseLong(a[0]));
					
				}
				vdcRepository.save(vdc);
			}
				
					
				
				
				
			
			
				
				
				
				

				

			catch (UnsupportedEncodingException e) {
			}
			
			}

		

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}


