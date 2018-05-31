//package com.softtech.localLevel.controller;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.util.Iterator;
//
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.softtech.localLevel.model.District;
//import com.softtech.localLevel.model.NewVdc;
//import com.softtech.localLevel.model.OldVdc;
//import com.softtech.localLevel.model.SDistrict;
//import com.softtech.localLevel.model.State;
//import com.softtech.localLevel.repository.DistrictRepository;
//import com.softtech.localLevel.repository.NewVdcRepository;
//import com.softtech.localLevel.repository.NewWardRepository;
//import com.softtech.localLevel.repository.OldVdcRepository;
//import com.softtech.localLevel.repository.OldWardRepository;
//import com.softtech.localLevel.repository.SDistrictRepository;
//import com.softtech.localLevel.repository.StateRepository;
//
//import io.swagger.annotations.ApiOperation;
//
//@RequestMapping("/rest")
//@RestController
//public class MainController {
//
//	@Autowired
//	private StateRepository stateRepository;
//	@Autowired
//	private DistrictRepository districtRepository;
//
//	@SuppressWarnings({ "null", "unused" })
//	@ApiOperation(value = "Upload an excel file ")
//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//	ResponseEntity<Object> processExcelSheet(@RequestParam("local") MultipartFile multipartFile) throws IOException {
//
//		InputStream stream = multipartFile.getInputStream();
//		XSSFWorkbook workbook = new XSSFWorkbook(stream);
//		XSSFSheet sheet = workbook.getSheetAt(0);
//		XSSFRow row;
//		XSSFCell cell;
//		Iterator rows = sheet.rowIterator();
//
//		int k = 0, counter = 2;
//		String temp = null;
//		String[] temp0 = new String[35083];
//		temp0[0] = null;
//		String[] temp1 = new String[35083];
//		temp1[0] = null;
//		String[] temp2 = new String[35083];
//		temp2[0] = null;
//		String[] temp3 = new String[35083];
//		temp3[0] = null;
//		String[] temp4 = new String[35083];
//		temp4[0] = null;
//		String[] temp5 = new String[35083];
//		temp5[0] = null;
//
//		while (rows.hasNext()) {
//			row = (XSSFRow) rows.next();
//			if (row.getRowNum() == 0) {
//				continue;
//			}
//
//			try {
//
//				
//
//				SDistrict district = new SDistrict();
//				String string1 = row.getCell(1).toString();
//				byte[] u1 = string1.getBytes("UTF-8");
//				string1 = new String(u1, "UTF-8");
//				for (int j = 0; j < k + 1; j++) {
//					if (!string1.equalsIgnoreCase(temp1[j])) {
//						counter = 0;
//					} else {
//						counter = 1;
//						break;
//					}
//
//				}
//				temp1[k + 1] = string1;
//				if (counter == 0) {
//					district.setDistrict(string1);
//					sDistrictRepository.save(district);
//					// System.out.println(district1.toString());
//				}
//
//			}
//
//			catch (UnsupportedEncodingException e) {
//			}
//			k++;
//
//		}
//
//		return new ResponseEntity<Object>(HttpStatus.OK);
//	}
//
//}
