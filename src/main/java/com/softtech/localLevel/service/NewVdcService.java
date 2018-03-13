package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOG = LoggerFactory.getLogger(NewVdcService.class);

	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	NewVdcRepository newVdcRepository;
	@Autowired
	OldVdcRepository oldVdcRepository;

	@Transactional
	public List<NewVdcResponseDto> ListAllNewVdc(String district) {
		LOG.info("\n\nRequest Accepted to list all new Vdcs\n");
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

	@Transactional
	public NewVdcResponseDto getNewVdc(String oldVdc) {
		LOG.info("\n\nRequest Accepted to show new vdc from old vdc\n");
		NewVdcResponseDto newVdcResponseDto = new NewVdcResponseDto();
		OldVdc oldVdcs = oldVdcRepository.findByOldVdc(oldVdc);
		System.out.println("The id is" + oldVdcs.getId());
		NewVdc newVdc = newVdcRepository.findById(oldVdcs.getNewVdc().getId());
		newVdcResponseDto.setNewVdc(newVdc.getNewVdc());
		return newVdcResponseDto;

	}

	@Transactional
	public VdcDetailDto getVdcDetail(String oldVdcName) {
		LOG.info("\n\nRequest Accepted to show new Vdcs detail from old Vdc\n");
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
