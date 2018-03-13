package com.softtech.localLevel.service;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger LOG=LoggerFactory.getLogger(OldVdcService.class);
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	NewVdcRepository newVdcRepository;
	@Autowired
	OldVdcRepository oldVdcRepository;
	
	@Transactional
	public List<OldVdcResponseDto> listAllOldVdc(String district) {

		LOG.info("Request accepted to list all old vdcs from district");
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
