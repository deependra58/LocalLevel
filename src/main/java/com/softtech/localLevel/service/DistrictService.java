package com.softtech.localLevel.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.NewVdc;
import com.softtech.localLevel.model.OldVdc;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.NewVdcRepository;
import com.softtech.localLevel.repository.OldVdcRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.DistrictResponseDto;
import com.softtech.localLevel.response.NewVdcResponseDto;
import com.softtech.localLevel.response.OldVdcResponseDto;

@Service
public class DistrictService {
	
	private static final Logger LOG=LoggerFactory.getLogger(DistrictService.class);
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	StateRepository stateRepository;

	@Transactional
	public List<DistrictResponseDto> listAllDistricts(String state) {

		LOG.info("Request Accepted to list all districts from state name");
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
	@Transactional
	public List<DistrictResponseDto> listAllDistricts() {
		List<DistrictResponseDto> districtResponseDtoList = new ArrayList<DistrictResponseDto>();
		List<District> districts = districtRepository.findAll();

		districts.stream().forEach(u -> {
			DistrictResponseDto districtResponseDto = new DistrictResponseDto();
			districtResponseDto.setDistrict(u.getDistrict());

			districtResponseDtoList.add(districtResponseDto);

		});
		return districtResponseDtoList;

	}

}
