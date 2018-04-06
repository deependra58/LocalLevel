package com.softtech.localLevel.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.request.StateCreationRequest;
import com.softech.localLevel.request.StateEditRequest;
import com.softtech.localLevel.dto.StateDetailDto;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.ProtectedAreas;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.LakesResponseDto;
import com.softtech.localLevel.response.MountainsResponseDto;
import com.softtech.localLevel.response.NaturalResourcesDtoList;
import com.softtech.localLevel.response.ProtectedAreaResponseDto;

import com.softtech.localLevel.response.RiversResponseDto;
import com.softtech.localLevel.response.WaterfallResponseDto;
import com.softtech.localLevel.service.LakesService;
import com.softtech.localLevel.service.MountainService;
import com.softtech.localLevel.service.ProtectedAreasService;
import com.softtech.localLevel.service.RiversService;
import com.softtech.localLevel.service.StateService;
import com.softtech.localLevel.service.WaterfallService;
import com.softtech.localLevel.util.LocalLevelType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/rest/states")
@Api(value="State Controller", description="Operation about states")
public class StateController {
	 private static String UPLOADED_FOLDER ="";
	@Autowired
	private StateService stateService;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private MountainService mountainService;
	
	@Autowired
	private RiversService riversService;
	
	@Autowired
	private LakesService LakesService;
	
	@Autowired
	private WaterfallService waterfallService;
	
	@Autowired
	private ProtectedAreasService protectedAreasService;
	
	@ApiOperation(value = "Upload an excel file for State")
	@RequestMapping(value = "/uploadState", method = RequestMethod.POST)
	ResponseEntity<Object> processExcelSheet(@RequestParam("States") MultipartFile multipartFile)
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
		
		State st=stateRepository.findByState("state 7");
		if(st!=null) {
			
			throw new AlreadyExistException("State file has been already uploaded into the database.");
		}

		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}

			try {
				
				

				State state = new State();
				String string0 = row.getCell(1).toString();
				byte[] u0 = string0.getBytes("UTF-8");
				string0 = new String(u0, "UTF-8");
				state.setState(string0);

				String string1 = row.getCell(2).toString();
				byte[] u1 = string1.getBytes("UTF-8");
				string1 = new String(u1, "UTF-8");
				state.setCapital(string1);
				
				
				

				String string2 = row.getCell(5).toString();
				byte[] u2 = string2.getBytes("UTF-8");
				string2 = new String(u2, "UTF-8");
				state.setArea(string2);

				String string3 = row.getCell(6).toString();
				byte[] u3 = string3.getBytes("UTF-8");
				string3 = new String(u3, "UTF-8");
				String[] a1=string3.split(Pattern.quote("."));
				state.setPopulation(a1[0]);
				
				String string4 = row.getCell(3).toString();
				byte[] u4 = string4.getBytes("UTF-8");
				string4 = new String(u4, "UTF-8");
				state.setGoverner(string4);
				
				String string5 = row.getCell(4).toString();
				byte[] u5 = string5.getBytes("UTF-8");
				string5 = new String(u5, "UTF-8");
				state.setChiefMinister(string5);
				
				
				String string6 = row.getCell(7).toString();
				byte[] u6 = string6.getBytes("UTF-8");
				string6 = new String(u6, "UTF-8");
				String[] a2=string6.split(Pattern.quote("."));
				state.setDensity(a2[0]);
				
				String string7 = row.getCell(8).toString();
				byte[] u7 = string7.getBytes("UTF-8");
				string7 = new String(u7, "UTF-8");
				state.setWebsite(string7);
				
				

				state.setLocalLevelType(LocalLevelType.STATE);

				stateRepository.save(state);
			}

			catch (UnsupportedEncodingException e) {
			}

		}

		return new ResponseEntity<Object>("State uploaded!",HttpStatus.OK);
	}

	
	@ApiOperation(value="Post the state details manually", notes="Post state details manually")
	@RequestMapping(value="state", method=RequestMethod.POST)
	public ResponseEntity<Object> postState(@RequestBody StateCreationRequest stateCreationRequest){
		stateService.createState(stateCreationRequest);
		return new ResponseEntity<Object>(HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Shows the state details", notes="Shows the details regarding state")
	@RequestMapping(value = "stateDetails/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> listAllDistricts(@PathVariable("state") String state) {
		StateDetailDto stateDetaildto = stateService.getStateDetails(state);
		System.out.println(stateDetaildto.getStatePicture());
		return new ResponseEntity<Object>(stateDetaildto, HttpStatus.OK);
	}
	
	@ApiOperation(value="Edit state information", notes="Edit state information")
	@RequestMapping(value="editState/{stateId}", method=RequestMethod.PUT)
	public ResponseEntity<Object> editState(@RequestBody StateEditRequest stateEditRequest, @RequestParam(required=false) String state,@RequestHeader(required=false) Long stateId){
		State st=stateService.editState(stateEditRequest,state, stateId);
		return new ResponseEntity<Object>(st,HttpStatus.OK);
		
	}
	
	
	@ApiOperation(value="Post the state picture", notes="Post the state picture")
	@RequestMapping(value="stateImg/{state:.+}",method=RequestMethod.POST)
	public ResponseEntity<Object> postPicture(@RequestBody String string ,@RequestParam String state){
	stateService.postPicture(string,state);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@ApiOperation(value="Get natural resources")
	@RequestMapping(value="naturalResources/{state:.+}",method=RequestMethod.GET)
	public ResponseEntity<Object> getNaturalResources(String state){
		
		List<MountainsResponseDto> mountainsResponseDtos=mountainService.getMountain(state);
		List<RiversResponseDto> riversResponseDtos=riversService.getRivers(state);
		List<LakesResponseDto> lakesResponseDtos=LakesService.getLakes(state);
		List<WaterfallResponseDto> waterfallResponseDtos=waterfallService.getWaterfall(state);
		List<ProtectedAreaResponseDto> protectedAreasResponseDtos=protectedAreasService.getProtectedArea(state);
		NaturalResourcesDtoList naturalResourcesDtoList=new NaturalResourcesDtoList(mountainsResponseDtos,riversResponseDtos,lakesResponseDtos,waterfallResponseDtos,protectedAreasResponseDtos);
		return new ResponseEntity<Object> (naturalResourcesDtoList,HttpStatus.OK);
		
	}
	
	/*==========================================================================================================*/

/*	@RequestMapping(value = "url to the controller method", method = RequestMethod.POST)
	public String createImage(@RequestParam("image") MultipartFile image){

	    try {
	        byte[] design = image.getBytes();

	        System.out.println(design.toString());

	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return "redirect:/home";
	}*/
	
	@PostMapping("/upload") 
    public ResponseEntity<Object> singleFileUpload(@RequestParam("file") MultipartFile file,@RequestHeader Long stateId) throws IOException{

      stateService.storeImage(file,stateId);
      return new ResponseEntity<Object>(HttpStatus.OK);
       
        /*File convertFile=new File(""+file.getOriginalFilename());
        convertFile.createNewFile();
        System.out.println("" + convertFile);
        FileOutputStream fout=new FileOutputStream(convertFile);
        System.out.println("" + fout);
        fout.write(file.getBytes());
        fout.close();
        return "File uploaded";*/
    }

	
	
}
