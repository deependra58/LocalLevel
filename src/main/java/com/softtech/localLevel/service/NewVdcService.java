package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softtech.localLevel.dto.VdcDetailDto;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.NewVdc;
import com.softtech.localLevel.model.OldVdc;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.NewVdcRepository;
import com.softtech.localLevel.repository.OldVdcRepository;
import com.softtech.localLevel.response.NewVdcResponseDto;
import com.softtech.localLevel.response.OldVdcResponseDto;

@Service
public class NewVdcService {

	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	NewVdcRepository newVdcRepository;
	@Autowired
	OldVdcRepository oldVdcRepository;

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
	}

	public NewVdcResponseDto getNewVdc(String oldVdc) {

		NewVdcResponseDto newVdcResponseDto = new NewVdcResponseDto();
		OldVdc oldVdcs = oldVdcRepository.findByOldVdc(oldVdc);
		System.out.println("The id is" + oldVdcs.getId());
		NewVdc newVdc = newVdcRepository.findById(oldVdcs.getNewVdc().getId());
		newVdcResponseDto.setNewVdc(newVdc.getNewVdc());
		return newVdcResponseDto;

	}

	public VdcDetailDto getVdcDetail(String oldVdcName) {

		OldVdc oldVdcs = oldVdcRepository.findByOldVdc(oldVdcName);
		System.out.println("The id is" + oldVdcs.getId());
		NewVdc newVdc = newVdcRepository.findById(oldVdcs.getNewVdc().getId());
		VdcDetailDto vdcDetailDto = new VdcDetailDto();
		List<OldVdcResponseDto> oldVdcList = new ArrayList<OldVdcResponseDto>();
		vdcDetailDto.setNewVdc(newVdc.getNewVdc());
		vdcDetailDto.setHead(newVdc.getHead());
		vdcDetailDto.setSubHead(newVdc.getSubHead());
		vdcDetailDto.setPopulation(newVdc.getPopulation());
		vdcDetailDto.setArea(newVdc.getArea());
		vdcDetailDto.setEmail(newVdc.getEmail());

		List<OldVdc> oldVdc = oldVdcRepository.findAllByNewVdc(new NewVdc(newVdc.getId()));
		oldVdc.stream().forEach(u -> {

			OldVdcResponseDto oldVDcs = new OldVdcResponseDto();
			oldVDcs.setOldVdc(u.getOldVdc());
			oldVdcList.add(oldVDcs);
			vdcDetailDto.setOldVdc(oldVdcList);

		});
		return vdcDetailDto;

	}
}
