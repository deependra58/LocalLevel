package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Hospital;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.HospitalRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.HospitalResponseDto;
import com.softtech.localLevel.util.Status;

@Service
public class HospitalService {
	
	@Autowired
	DistrictRepository districtRepository;
	
	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	HospitalRepository hospitalRepository;

	public Hospital postHospital(String hospitalName, String district, String hospitalContactNumber, String description, String address) {
		
		Hospital infrac = new Hospital();
		District districts = districtRepository.findByDistrict(district);
		if (districts == null) {
			throw new NotFoundException("District with name " + district + " not found.");
		}
		Hospital infracs = hospitalRepository.findByHospitalAndStatusNot(hospitalName,Status.DELETED);
		if (infracs != null) {
			throw new AlreadyExistException("Hospital with name " + hospitalName + " already exist.");
		}
		infrac.setDistrict(districts);
		infrac.setHospital(hospitalName);
		infrac.setContactNumber(hospitalContactNumber);
		infrac.setDescription(description);
		infrac.setAddress(address);
		State state = stateRepository.findById(districts.getState().getId());
		infrac.setState(state);
		infrac.setStatus(Status.ACTIVE);
		hospitalRepository.save(infrac);

		return infrac;
		
	}

	public List<HospitalResponseDto> getHospitalDetail(String state) {
		State states = stateRepository.findByState(state);
		List<Hospital> infrastructures = hospitalRepository.findAllByStateAndStatusNot(new State(states.getId()),Status.DELETED);
		List<HospitalResponseDto> hospitalResponseDtosList = new ArrayList<HospitalResponseDto>();
		infrastructures.stream().forEach(u -> {
		
				HospitalResponseDto hospitalResponseDto = new HospitalResponseDto();
				hospitalResponseDto.setHospital(u.getHospital());
				hospitalResponseDto.setHospitalContactNumber(u.getContactNumber());
				hospitalResponseDto.setDescription(u.getAddress());
				hospitalResponseDto.setAddresString(u.getAddress());
				
				District district = districtRepository.findById(u.getDistrict().getId());
				hospitalResponseDto.setDistrict(district.getDistrict());
				hospitalResponseDto.setHospitalContactNumber(u.getContactNumber());
				hospitalResponseDto.setHospitalImage(u.getHospitalImage());
				hospitalResponseDtosList.add(hospitalResponseDto);
			
		});

		return hospitalResponseDtosList;
	}

}
