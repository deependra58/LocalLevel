package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.NewVdc;
import com.softtech.localLevel.model.OldVdc;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.NewVdcRepository;
import com.softtech.localLevel.repository.OldVdcRepository;
import com.softtech.localLevel.response.OldVdcResponseDto;

@Service
public class OldVdcService {
	
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	NewVdcRepository newVdcRepository;
	@Autowired
	OldVdcRepository oldVdcRepository;
	public List<OldVdcResponseDto> listAllOldVdc(String district) {

		List<OldVdcResponseDto> oldVdcResponseDtoList = new ArrayList<OldVdcResponseDto>();
		District districts = districtRepository.findByDistrict(district);
		// Long id=districts.getId();
		List<NewVdc> newVdcs = newVdcRepository.findAllByDistrict(new District(districts.getId()));
		newVdcs.stream().forEach(u -> {
			/*
			 * NewVdcResponseDto newVdcResponseDto = new NewVdcResponseDto();
			 * OldVdcResponseDto oldVdcResponseDto = new OldVdcResponseDto();
			 */
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

}
