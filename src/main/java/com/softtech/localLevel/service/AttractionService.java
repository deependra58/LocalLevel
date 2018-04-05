package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.Attraction;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Infrastructure;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.AttractionRepository;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.AttractionResponseDto;
import com.softtech.localLevel.response.HospitalResponseDto;

@Service
public class AttractionService {

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private AttractionRepository attractionRepository;

	@Autowired
	private StateRepository stateRepository;

	@Transactional
	public Attraction postFamousFor(String item, String district,String description) {
		Attraction fm = new Attraction();
		District dist = districtRepository.findByDistrict(district);
		if (dist == null) {
			throw new NotFoundException("District with name " + district + " not found.");
		}
		fm.setDistrict(dist);
		fm.setItem(item);
		fm.setDescription(description);
		State state = stateRepository.findById(dist.getState().getId());
		fm.setState(state);
		attractionRepository.save(fm);
		return fm;

	}

	public List<AttractionResponseDto> getAttaction(String state) {
		State states = stateRepository.findByState(state);
		List<Attraction> attractions = attractionRepository.findAllByState(new State(states.getId()));
		List<AttractionResponseDto> attractionResponseDtoList = new ArrayList<AttractionResponseDto>();
		attractions.stream().forEach(u -> {
			if (u.getItem() != null) {
				AttractionResponseDto attractionResponseDto = new AttractionResponseDto();
				attractionResponseDto.setItem(u.getItem());
				District district = districtRepository.findById(u.getDistrict().getId());
				attractionResponseDto.setDistrict(district.getDistrict());
				attractionResponseDto.setDescription(u.getDescription());
				attractionResponseDtoList.add(attractionResponseDto);
			}
		});

		return attractionResponseDtoList;
	}

}
