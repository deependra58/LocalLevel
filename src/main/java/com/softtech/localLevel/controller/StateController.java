package com.softtech.localLevel.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import com.softtech.localLevel.dto.StateDetailDto;
import com.softtech.localLevel.service.StateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



@RestController
@RequestMapping("/rest/states")
@Api(value="State Controller", description="Operation about states")
public class StateController {
	 private static String UPLOADED_FOLDER ="";
	@Autowired
	private StateService stateService;
	
	
	@ApiOperation(value="Shows the state details", notes="Shows the details regarding state")
	@RequestMapping(value = "state/{state:.+}", method = RequestMethod.GET)
	public ResponseEntity<Object> listAllDistricts(@PathVariable("state") String state) {
		StateDetailDto stateDetaildto = stateService.getStateDetails(state);
		System.out.println(stateDetaildto.getStatePicture());
		return new ResponseEntity<Object>(stateDetaildto, HttpStatus.OK);
	}
	
	
	@ApiOperation(value="Post the state picture", notes="Post the state picture")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> postPicture(@RequestBody String string ,@RequestHeader Long stateId){
	stateService.postPicture(string,stateId);
		return new ResponseEntity<Object>(HttpStatus.OK);
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
