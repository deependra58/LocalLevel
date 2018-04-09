package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Industry;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.IndustryRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.IndustriesResponseDto;
import com.softtech.localLevel.util.Status;

@Service
public class IndustryService {
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private IndustryRepository industryRepository;

	public Industry postIndustry(String industryName, String district, String description, String address) {
		Industry infrac = new Industry();
		District districts = districtRepository.findByDistrict(district);
		if (districts == null) {
			throw new NotFoundException("District with name " + district + " not found.");
		}
		Industry infracs = industryRepository.findByIndustryAndStatusNot(industryName,Status.DELETED);
		if (infracs != null) {
			throw new AlreadyExistException("Industry with name " + industryName + " already exist.");
		}
		infrac.setDistrict(districts);
		infrac.setIndustry(industryName);
		infrac.setAddress(address);
		infrac.setDescription(description);
		infrac.setStatus(Status.ACTIVE);
		State state = stateRepository.findById(districts.getState().getId());
		infrac.setState(state);
		industryRepository.save(infrac);

		return infrac;
	}

	public List<IndustriesResponseDto> getIndustries(String state) {
		State states = stateRepository.findByState(state);
		List<Industry> infrastructures = industryRepository.findAllByStateAndStatusNot(new State(states.getId()),Status.DELETED);
		List<IndustriesResponseDto> industriesResponseDtoList = new ArrayList<IndustriesResponseDto>();
		infrastructures.stream().forEach(u -> {
			
				IndustriesResponseDto industriesResponseDto = new IndustriesResponseDto();
				industriesResponseDto.setIndustry(u.getIndustry());
				industriesResponseDto.setIndustryImage(u.getIndustryImage());
				industriesResponseDto.setAddress(u.getAddress());
				industriesResponseDto.setDescription(u.getDescription());
				District district = districtRepository.findById(u.getDistrict().getId());
				industriesResponseDto.setDistrict(district.getDistrict());
				industriesResponseDtoList.add(industriesResponseDto);
			
		});

		return industriesResponseDtoList;
	}

}
