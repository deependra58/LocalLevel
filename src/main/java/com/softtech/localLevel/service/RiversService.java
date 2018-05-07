package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.District;

import com.softtech.localLevel.model.Rivers;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.RiversRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.RiversResponseDto;
import com.softtech.localLevel.util.Status;

@Service
public class RiversService {

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private RiversRepository riversRepository;

	@Autowired
	private StateRepository stateRepository;

	public Rivers postRiver(String river_name, String districts, String description) {
		Rivers naturalResources = new Rivers();
		District district = districtRepository.findByDistrictAndStatusNot(districts, Status.DELETED);
		if (district == null) {
			throw new NotFoundException("District with name " + districts + " not found.");
		}
		Rivers naturalRes = riversRepository.findByRiver(river_name);
		if (naturalRes != null) {
			throw new AlreadyExistException("River with name " + river_name + " already exist.");
		}
		//naturalResources.setDistrict(district);
		naturalResources.setRiver(river_name);
		naturalResources.setDescription(description);
		State state = stateRepository.findById(district.getState().getId());
		naturalResources.setState(state);
		naturalResources.setStatus(Status.ACTIVE);
		riversRepository.save(naturalResources);
		return naturalResources;
	}

	public List<RiversResponseDto> getRivers(String state) {
		State states = stateRepository.findByState(state);
		List<Rivers> naturalResources = riversRepository.findAllByState(new State(states.getId()));
		// System.out.println("Rivers:"+naturalResources.toString());
		List<RiversResponseDto> riversResponseDtoList = new ArrayList<RiversResponseDto>();
		naturalResources.stream().forEach(u -> {

			RiversResponseDto riversResponseDto = new RiversResponseDto();
//			District district = districtRepository.findById(u.getDistrict().getId());
//			riversResponseDto.setDistrict(district.getDistrict());
			riversResponseDto.setRiver(u.getRiver());
			riversResponseDto.setDescription(u.getDescription());
			riversResponseDto.setRiverImage(u.getRiverImageString());
			riversResponseDtoList.add(riversResponseDto);
			 System.out.println("Rivers:"+riversResponseDto.toString());
			

		});
		return riversResponseDtoList;

	}

}
