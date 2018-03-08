/*package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.StateRepository;

import com.softtech.localLevel.response.DistrictResponseDto;

@Service
public class StateService {
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	StateRepository stateRepository;
	
@Transactional
	public List<DistrictResponseDto> listAllDistricts(String state) {

		List<DistrictResponseDto> districtResponseDtoList = new ArrayList<DistrictResponseDto>();

		State states = stateRepository.findByState(state);
		Long id = states.getId();
		List<District> districts = districtRepository.findAllByState(new State(id));

		districts.stream().forEach(u -> {
			DistrictResponseDto districtResponseDto = new DistrictResponseDto();
			districtResponseDto.setDistrict(u.getDistrict());

			districtResponseDtoList.add(districtResponseDto);

		});
		return districtResponseDtoList;

	}

}

*/