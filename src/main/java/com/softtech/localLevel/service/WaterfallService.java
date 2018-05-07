package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.District;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.model.Waterfall;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.repository.WaterfallRepository;
import com.softtech.localLevel.response.WaterfallResponseDto;
import com.softtech.localLevel.util.Status;

@Service
public class WaterfallService {

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private WaterfallRepository waterfallRepository;
	
	public Waterfall postWaterfall(String waterfall_name, String districts,String description) {
		Waterfall naturalResources = new Waterfall();
		District district = districtRepository.findByDistrictAndStatusNot(districts, Status.DELETED);
		if (district == null) {
			throw new NotFoundException("District with name " + districts + " not found.");
		}
		Waterfall naturalRes = waterfallRepository.findByWaterfall(waterfall_name);
		if (naturalRes != null) {
			throw new AlreadyExistException("Waterfall with name " + waterfall_name + " already exist.");
		}
		naturalResources.setDistrict(district);
		naturalResources.setWaterfall(waterfall_name);
		State state = stateRepository.findById(district.getState().getId());
		naturalResources.setState(state);
		naturalResources.setDescription(description);
		naturalResources.setStatus(Status.ACTIVE);
		waterfallRepository.save(naturalResources);
		return naturalResources;
	}

	public List<WaterfallResponseDto> getWaterfall(String state) {
		State states = stateRepository.findByState(state);
		List<Waterfall> naturalResources = waterfallRepository.findAllByState(new State(states.getId()));
		List<WaterfallResponseDto> waterfallResponseDtoList = new ArrayList<WaterfallResponseDto>();
		naturalResources.stream().forEach(u -> {
			
				WaterfallResponseDto waterfallResponseDto = new WaterfallResponseDto();
				District district=districtRepository.findById(u.getDistrict().getId());
				waterfallResponseDto.setDistrict(district.getDistrict());
				waterfallResponseDto.setWaterfall(u.getWaterfall());
				waterfallResponseDto.setDescription(u.getDescription());
				waterfallResponseDto.setWaterfallImage(u.getWaterfallImage());
				waterfallResponseDtoList.add(waterfallResponseDto);
		
		});
		return waterfallResponseDtoList;
	}

}
