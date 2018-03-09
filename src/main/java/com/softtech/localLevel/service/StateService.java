package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.localLevel.dto.StateDetailDto;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.StateRepository;

import com.softtech.localLevel.response.DistrictResponseDto;

@Service
public class StateService {
	
	@Autowired
	private StateRepository stateRepository;

	public StateDetailDto getStateDetails(String state) {
		
		StateDetailDto stateDetailDto=new StateDetailDto();
		State states=stateRepository.findByState(state);
		stateDetailDto.setWebsite(states.getWebsite());
		stateDetailDto.setArea(states.getArea());
		stateDetailDto.setPopulation(states.getPopulation());
		stateDetailDto.setMayor(states.getMayor());
		stateDetailDto.setDeputMayor(states.getDeputMayor());
		
		return stateDetailDto;
	}


}

