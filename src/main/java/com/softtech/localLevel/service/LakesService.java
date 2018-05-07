package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Lakes;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.LakeRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.LakesResponseDto;
import com.softtech.localLevel.util.Status;

@Service
public class LakesService {

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private LakeRepository lakeRepository;

	@Autowired
	private StateRepository stateRepository;

	public Lakes postLake(String lake_name, String districts, String description) {
		Lakes naturalResources = new Lakes();
		District district = districtRepository.findByDistrictAndStatusNot(districts, Status.DELETED);
		if (district == null) {
			throw new NotFoundException("District with name " + districts + " not found.");
		}
		Lakes naturalRes = lakeRepository.findBylakes(lake_name);
		if (naturalRes != null) {
			throw new AlreadyExistException("lake with name " + lake_name + " already exist.");
		}
		naturalResources.setDistrict(district);
		naturalResources.setLakes(lake_name);
		naturalResources.setDescription(description);
		State state = stateRepository.findById(district.getState().getId());
		naturalResources.setState(state);
		lakeRepository.save(naturalResources);
		return naturalResources;
	}

	public List<LakesResponseDto> getLakes(String state) {
		State states = stateRepository.findByState(state);
		List<Lakes> naturalResources = lakeRepository.findAllByState(new State(states.getId()));
		List<LakesResponseDto> lakesResponseDtoList = new ArrayList<LakesResponseDto>();
		naturalResources.stream().forEach(u -> {

			LakesResponseDto lakesResponseDto = new LakesResponseDto();
			District district = districtRepository.findById(u.getDistrict().getId());
			lakesResponseDto.setDistrict(district.getDistrict());
			lakesResponseDto.setLake(u.getLakes());
			lakesResponseDto.setLakeImageString(u.getLakeImage());
			lakesResponseDto.setDescriptionString(u.getDescription());
			lakesResponseDtoList.add(lakesResponseDto);

		});
		return lakesResponseDtoList;
	}

}
