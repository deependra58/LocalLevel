package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
	/*
	 * public List<NewVdcResponseDto> listAllNewVdc(String district) {
	 * List<NewVdcResponseDto> newVDcResponseDtoList = new
	 * ArrayList<NewVdcResponseDto>();
	 * 
	 * District districts = districtRepository.findByDistrict(district); Long id =
	 * districts.getId(); List<NewVdc> newVdcs =
	 * newVdcRepository.findAllByDistrict(new District(id));
	 * 
	 * newVdcs.stream().forEach(u -> { NewVdcResponseDto newVdcResponseDto = new
	 * NewVdcResponseDto(); newVdcResponseDto.setNewVdc(u.getNewVdc());
	 * 
	 * newVDcResponseDtoList.add(newVdcResponseDto);
	 * 
	 * }); return newVDcResponseDtoList;
	 * 
	 * }
	 */

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

	/*public List<OldVdcResponseDto> listAllOldVdc(String district) {

		List<OldVdcResponseDto> oldVdcResponseDtoList = new ArrayList<OldVdcResponseDto>();
		District districts = districtRepository.findByDistrict(district);
		// Long id=districts.getId();
		List<NewVdc> newVdcs = newVdcRepository.findAllByDistrict(new District(districts.getId()));
		newVdcs.stream().forEach(u -> {
			
			 * NewVdcResponseDto newVdcResponseDto = new NewVdcResponseDto();
			 * OldVdcResponseDto oldVdcResponseDto = new OldVdcResponseDto();
			 
			Long id = u.getId();
			List<OldVdc> oldVdcs = oldVdcRepository.findAllByNewVdc(new NewVdc(id));

			oldVdcs.stream().forEach(v -> {
				OldVdcResponseDto oldVdcResponseDto = new OldVdcResponseDto();
				oldVdcResponseDto.setOldVdc(v.getOldVdc());

				oldVdcResponseDtoList.add(oldVdcResponseDto);

			});

		});

		return oldVdcResponseDtoList;
	}

	public List<NewVdcResponseDto> ListAllNewVdc(String district) {
		List<NewVdcResponseDto> newVdcResponseDtoList = new ArrayList<NewVdcResponseDto>();
		District districts = districtRepository.findByDistrict(district);
		List<NewVdc> newVdcs = newVdcRepository.findAllByDistrict(new District(districts.getId()));
		newVdcs.stream().forEach(u -> {
			NewVdcResponseDto newVdcResponseDto = new NewVdcResponseDto();
			newVdcResponseDto.setNewVdc(u.getNewVdc());
			newVdcResponseDtoList.add(newVdcResponseDto);

		});

		return newVdcResponseDtoList;
	}*/

}

