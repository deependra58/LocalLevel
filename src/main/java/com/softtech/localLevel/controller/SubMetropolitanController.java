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

import com.softtech.localLevel.model.SubMetropolitan;
import com.softtech.localLevel.repository.SubMetropolitanRepository;
import com.softtech.localLevel.util.LocalLevelType;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rest")
public class SubMetropolitanController {

	@Autowired
	private SubMetropolitanRepository subMetropolitanRepository;

	@ApiOperation(value = "Upload an excel file for Sub-Metropolitan")
	@RequestMapping(value = "/uploadSubMetropolitan", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheet(@RequestParam("Sub-metropolitan") MultipartFile multipartFile)
			throws IOException {

		InputStream stream = multipartFile.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(stream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator rows = sheet.rowIterator();

		int k = 0, counter = 2;
		String temp = null;
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

				SubMetropolitan subMetropolitan = new SubMetropolitan();
				String string0 = row.getCell(3).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				subMetropolitan.setSubMetropolitan(string0);

				String string1 = row.getCell(5).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				subMetropolitan.setPopulation(string1);

				String string2 = row.getCell(6).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				subMetropolitan.setArea(string2);

				String string3 = row.getCell(7).toString();
				byte[] u3 = string3.getBytes("UTF-8");
				string3 = new String(u3, "UTF-8");
				subMetropolitan.setDensity(string3);

				String string4 = row.getCell(8).toString();
				byte[] u4 = string4.getBytes("UTF-8");
				string4 = new String(u4, "UTF-8");
				subMetropolitan.setWebsite(string4);

				String string5 = row.getCell(9).toString();
				byte[] u5 = string5.getBytes("UTF-8");
				string5 = new String(u5, "UTF-8");
				subMetropolitan.setMayor(string5);

				String string6 = row.getCell(10).toString();
				byte[] u6 = string6.getBytes("UTF-8");
				string6 = new String(u6, "UTF-8");
				subMetropolitan.setDeputMayor(string6);
				
				subMetropolitan.setLocalLevelType(LocalLevelType.SUBMETROPOLITAN);

				subMetropolitanRepository.save(subMetropolitan);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
