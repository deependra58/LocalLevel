package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Mountains;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.MountainsRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.MountainsResponseDto;
import com.softtech.localLevel.util.Status;

@Service
public class MountainService {


	@Autowired
	private MountainsRepository mountainsRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private StateRepository stateRepository;

	public Mountains postMountain(String mountain_name, String mountainHeight, String districts, String description) {
		Mountains naturalResources = new Mountains();
		District district = districtRepository.findByDistrictAndStatusNot(districts, Status.DELETED);
		if (district == null) {
			throw new NotFoundException("District with name " + districts + " not found.");
		}
		Mountains naturalRes = mountainsRepository.findByMountain(mountain_name);
		if (naturalRes != null) {
			throw new AlreadyExistException("Mountain with name " + mountain_name + " already exist.");
		}
		naturalResources.setDistrict(district);
		naturalResources.setMountain(mountain_name);
		State state = stateRepository.findById(district.getState().getId());
		naturalResources.setState(state);
		naturalResources.setHeight(mountainHeight);
		naturalResources.setDescription(description);
		naturalResources.setStatus(Status.ACTIVE);
		mountainsRepository.save(naturalResources);

		return naturalResources;
	}

	public List<MountainsResponseDto> getMountain(String state) {
		
		List<MountainsResponseDto> mountainResponseDtoList = new ArrayList<MountainsResponseDto>();
		State states = stateRepository.findByState(state);
		List<Mountains> naturalResources = mountainsRepository.findAllByState(new State(states.getId()));
		naturalResources.stream().forEach(u -> {
			MountainsResponseDto mountainsResponseDto = new MountainsResponseDto();
			mountainsResponseDto.setMountain(u.getMountain());
			mountainsResponseDto.setMountainHeight(u.getHeight());
	 		District district = districtRepository.findById(u.getDistrict().getId());
			//System.out.println(district.toString());
			mountainsResponseDto.setDistrict(district.getDistrict());
			mountainsResponseDto.setDescription(u.getDescription());
			mountainsResponseDto.setMountainImage(u.getMountainImage());
			//System.out.println("\nMountain Details:"+mountainsResponseDto.toString());
			mountainResponseDtoList.add(mountainsResponseDto);

		});
		return mountainResponseDtoList;

	}

}
