package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Hydropower;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.HydropowerRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.HydropowerResponseDto;
import com.softtech.localLevel.util.Status;

@Service
public class HydropowerService {
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	DistrictRepository districtRepository;
	
	@Autowired
	HydropowerRepository hydropowerRepository;

	public Hydropower postHydropower(String hydropowerName, String district, String capacity, String description,String address,String hydroStatus) {
		Hydropower infrac = new Hydropower();
		District districts = districtRepository.findByDistrict(district);
		if (districts == null) {
			throw new NotFoundException("District with name " + district + " not found.");
		}
		Hydropower infracs = hydropowerRepository.findByHydropowerAndStatusNot(hydropowerName,Status.DELETED);
		if (infracs != null) {
			throw new AlreadyExistException("Hydropower with name " + hydropowerName + " already exist.");
		}
		infrac.setDistrict(districts);
		infrac.setCapacity(capacity);
		infrac.setHydropower(hydropowerName);
		infrac.setDescription(description);
		infrac.setAddress(address);
		State state = stateRepository.findById(districts.getState().getId());
		infrac.setState(state);
		infrac.setHydroStatus(hydroStatus);
		infrac.setStatus(Status.ACTIVE);
		hydropowerRepository.save(infrac);

		return infrac;
	}

	public List<HydropowerResponseDto> getHydropower(String state) {
		State states = stateRepository.findByState(state);
		List<Hydropower> infrastructures = hydropowerRepository.findAllByStateAndStatusNot(new State(states.getId()),Status.DELETED);
		List<HydropowerResponseDto> hydropowerResponseDtoList = new ArrayList<HydropowerResponseDto>();
		infrastructures.stream().forEach(u -> {
			
				HydropowerResponseDto hydropowerResponseDto = new HydropowerResponseDto();
				hydropowerResponseDto.setHydropower(u.getHydropower());
				District district = districtRepository.findById(u.getDistrict().getId());
				hydropowerResponseDto.setDistrict(district.getDistrict());
				hydropowerResponseDto.setDescription(u.getDescription());
				hydropowerResponseDto.setAddress(u.getAddress());
				hydropowerResponseDto.setCapacity(u.getCapacity());
				hydropowerResponseDto.setHydropowerImage(u.getHydropowerImage());
				hydropowerResponseDto.setHydroStatus(u.getHydroStatus());
				hydropowerResponseDtoList.add(hydropowerResponseDto);
			
		});

		return hydropowerResponseDtoList;
	}

}
