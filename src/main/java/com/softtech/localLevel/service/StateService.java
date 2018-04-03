package com.softtech.localLevel.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softech.localLevel.request.StateCreationRequest;
import com.softech.localLevel.request.StateEditRequest;
import com.softtech.localLevel.dto.DistrictDto;
import com.softtech.localLevel.dto.StateDetailDto;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.util.FileUtil;
import com.softtech.localLevel.util.FileUtility;


@Service
public class StateService {

	private static final Logger LOG = LoggerFactory.getLogger(StateService.class);
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private DistrictRepository districtRepository;

	@Transactional
	public StateDetailDto getStateDetails(String state) {
		LOG.info("\n\nRequested accepted to show state details from state name\n");
		StateDetailDto stateDetailDto = new StateDetailDto();
		List<DistrictDto> districtDtoList = new ArrayList<DistrictDto>();
		State states = stateRepository.findByState(state);
		System.out.println("/n/nWebsite:" + states.getWebsite());
		stateDetailDto.setWebsite(states.getWebsite());

		stateDetailDto.setArea(states.getArea());
		stateDetailDto.setPopulation(states.getPopulation());
		stateDetailDto.setGoverner(states.getGoverner());
		stateDetailDto.setCapital(states.getCapital());
		stateDetailDto.setChiefMinister(states.getChiefMinister());
		stateDetailDto.setDensity(states.getDensity());
		//stateDetailDto.setMayorPhoneNumber(states.getMayorPhoneNumber());
		List<District> districts = districtRepository.findAllByState(new State(states.getId()));
		districts.stream().forEach(u -> {

			DistrictDto district = new DistrictDto();
			district.setDistrict(u.getDistrict());
			districtDtoList.add(district);
			stateDetailDto.setDistrict(districtDtoList);

		});
		/*if (states.getStatePicture() != null) {
			File file = new File(states.getStatePicture());
			try {
				stateDetailDto.setStatePicture(Base64Util.encodeFileToBase64Binary(file));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				file.deleteOnExit();
			}
		}*/
		stateDetailDto.setStatePicture(states.getStatePicture());
		

		return stateDetailDto;
	}

	public void postPicture(String imgString, String state) {
		File file = null;
		try {
			State states = stateRepository.findByState(state);

			LOG.info("Request Accepted to post state picture");
			file = FileUtil.write(String.valueOf(new Date().getTime()).concat(".").concat("png"), imgString);
			LOG.info("path {}", file.getAbsolutePath());
			if (file != null)
				states.setStatePicture(file.getAbsolutePath());
			stateRepository.save(states);

		} catch (Exception e) {
			e.printStackTrace();
			if (file != null) {
				file.delete();
			}
		}
	}
	


	public void storeImage(MultipartFile files, Long stateId) {
		File file = null;
		String fileName=null;
		try {
			State state = stateRepository.findById(stateId);

			LOG.info("Request Accepted to post state picture");
			file = FileUtility.writeFile(String.valueOf(new Date().getTime()).concat(".").concat("png"), files);
			System.out.println("/n/nHere"  +file);
			LOG.info("path {}", file.getAbsolutePath());
			System.out.println("File path:>"+ file.getAbsolutePath());
			if (file != null)
				
				state.setStatePicture(file.toString());
			System.out.println("Neew"+file.toString());
			
			stateRepository.save(state);

		} catch (Exception e) {
			e.printStackTrace();
			if (file != null) {
				file.delete();
			}
		}

	}

	@Transactional
	public void createState(StateCreationRequest stateCreationRequest) {
		
		State state=new State();
		state.setPopulation(stateCreationRequest.getPopulation());
		state.setArea(stateCreationRequest.getArea());
		state.setState(stateCreationRequest.getState());
		state.setGoverner(stateCreationRequest.getGoverner());
		state.setChiefMinister(stateCreationRequest.getChiefMinister());
		state.setWebsite(stateCreationRequest.getWebsite());
		state.setCapital(stateCreationRequest.getCapital());
		state.setDensity(stateCreationRequest.getDensity());
		//state.setMayorPhoneNumber(stateCreationRequest.getMayorPhoneNumber());
		stateRepository.save(state);
		
	}

	@Transactional
	public State editState(StateEditRequest stateEditRequest ,String states, Long stateId) {
		State state=null;
		if(states==null) {
			state=stateRepository.findById(stateId);
		}
		else {
			state=stateRepository.findByState(states);
		}
		state.setPopulation(stateEditRequest.getPopulation());
		state.setArea(stateEditRequest.getArea());
		state.setState(stateEditRequest.getState());
		state.setGoverner(stateEditRequest.getGoverner());
		state.setChiefMinister(stateEditRequest.getChiefMinister());
		state.setWebsite(stateEditRequest.getWebsite());
		state.setCapital(stateEditRequest.getCapital());
		state.setDensity(stateEditRequest.getDensity());
		//state.setMayorPhoneNumber(stateEditRequest.getMayorPhoneNumber());
		stateRepository.save(state);
		return state;
	}

	public void deleteState(Long stateId) {
		// TODO Auto-generated method stub
		State state=stateRepository.findById(stateId);
		stateRepository.delete(state);
		
	}
}
