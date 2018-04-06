package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.NaturalResources;
import com.softtech.localLevel.model.ProtectedAreas;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.ProtectedAreaRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.ProtectedAreaResponseDto;
import com.softtech.localLevel.util.Status;

@Service
public class ProtectedAreasService {
	
	@Autowired
	private StateRepository stateRepository;

	@Autowired
	DistrictRepository districtRepository;
	
	@Autowired
	private ProtectedAreaRepository protectedAreasRepository;

	public ProtectedAreas postProtectedArea(String protectedArea_name, String districts, String description,String area) {
		ProtectedAreas naturalResources = new ProtectedAreas();
		District district = districtRepository.findByDistrictAndStatusNot(districts, Status.DELETED);
		if (district == null) {
			throw new NotFoundException("District with name " + districts + " not found.");
		}
		ProtectedAreas naturalRes = protectedAreasRepository.findByProtectedAreas(protectedArea_name);
		if (naturalRes != null) {
			throw new AlreadyExistException("Protected Area with name " + protectedArea_name + " already exist.");
		}
		naturalResources.setDistrict(district);
		naturalResources.setProtectedAreas(protectedArea_name);
		State state = stateRepository.findById(district.getState().getId());
		naturalResources.setState(state);
		naturalResources.setDescription(description);
		naturalResources.setStatus(Status.ACTIVE);
		naturalResources.setArea(area);
		protectedAreasRepository.save(naturalResources);
		return naturalResources;

	}

	public List<ProtectedAreaResponseDto> getProtectedArea(String state) {
		State states = stateRepository.findByState(state);
		List<ProtectedAreas> naturalResources = protectedAreasRepository.findAllByState(new State(states.getId()));
		List<ProtectedAreaResponseDto> protectedAreaResponseDtoList = new ArrayList<ProtectedAreaResponseDto>();
		naturalResources.stream().forEach(u -> {
	
				ProtectedAreaResponseDto protectedAreaResponseDto = new ProtectedAreaResponseDto();
				District district=districtRepository.findById(u.getDistrict().getId());
				protectedAreaResponseDto.setDistrict(district.getDistrict());
				protectedAreaResponseDto.setProtectedAreas(u.getProtectedAreas());
				protectedAreaResponseDto.setDescription(u.getDescription());
				protectedAreaResponseDto.setProtectedAreasImage(u.getProtectedAreasImage());
				protectedAreaResponseDto.setArea(u.getArea());
				protectedAreaResponseDtoList.add(protectedAreaResponseDto);
			
		});
		return protectedAreaResponseDtoList;
		
	}

}
