package com.softtech.localLevel.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.localLevel.dto.StateDetailDto;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.StateRepository;

@Service
public class StateService {
	
	private static final Logger LOG=LoggerFactory.getLogger(StateService.class);
	@Autowired
	private StateRepository stateRepository;
	
	

	@Transactional
	public StateDetailDto getStateDetails(String state) {
		LOG.info("\n\nRequested accepted to show state details from state name\n");
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

