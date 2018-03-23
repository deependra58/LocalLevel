package com.softtech.localLevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.request.DistrictCreationRequest;
import com.softech.localLevel.request.DistrictEditRequest;
import com.softtech.localLevel.dto.DistrictDetailsDto;
import com.softtech.localLevel.model.District;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;

import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.DistrictResponseDto;
import com.softtech.localLevel.util.Base64Util;
import com.softtech.localLevel.util.FileUtil;

@Service
public class DistrictService {

	private static final Logger LOG = LoggerFactory.getLogger(DistrictService.class);
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	StateRepository stateRepository;

	@Transactional
	public List<DistrictResponseDto> listAllDistricts(String state) {

		LOG.info("\n\nRequest Accepted to list all districts from state name\n");
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

	@Transactional
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

	@Transactional
	public DistrictDetailsDto getDistrictDetails(String district) {
		LOG.info("Request accepted to show district details");
		DistrictDetailsDto districtDetailsDto = new DistrictDetailsDto();
		District districts = districtRepository.findByDistrict(district);
		districtDetailsDto.setArea(districts.getArea());
		districtDetailsDto.setHeadquater(districts.getHeadquater());
		districtDetailsDto.setPopulation(districts.getPopulation());
		Long id = districts.getState().getId();
		// System.out.println("Id " + districts.getState().getId());
		State state = stateRepository.findById(id);
		districtDetailsDto.setState(state.getState());
		if (districts.getDistrictPicture() != null) {
			File file = new File(districts.getDistrictPicture());
			try {
				districtDetailsDto.setDistrictPicture(Base64Util.encodeFileToBase64Binary(file));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				file.deleteOnExit();
			}
		}

		return districtDetailsDto;
	}

	@Transactional
	public void postDistrictPicture(String string, Long districtId) {
		LOG.info("\n\nRequest accepted to post district picture.");
		File file = null;
		try {
			District district = districtRepository.findById(districtId);

			LOG.info("Request Accepted to post district picture");
			file = FileUtil.write(String.valueOf(new Date().getTime()).concat(".").concat("png"), string);
			LOG.info("path {}", file.getAbsolutePath());
			if (file != null)
				district.setDistrictPicture(file.getAbsolutePath());
			districtRepository.save(district);

		} catch (Exception e) {
			e.printStackTrace();
			if (file != null) {
				file.delete();
			}
		}
	}

	public void postDistrictDetail(DistrictCreationRequest districtCreationRequest) {
		District district = new District();
		district.setPopulation(districtCreationRequest.getPopulation());
		district.setArea(districtCreationRequest.getArea());
		// System.out.println(districtCreationRequest.getStateId());
		State state = stateRepository.findOne(districtCreationRequest.getStateId());
		district.setState(state);
		// district.setState(new State(districtCreationRequest.getStateId()));
		district.setDistrict(districtCreationRequest.getDistrict());
		district.setHeadquater(districtCreationRequest.getHeadquater());
		districtRepository.save(district);

	}

	public District editDistrict(DistrictEditRequest districtEditRequest, String districts, Long districtId) {
		District district =null;
		if(districts==null) {
			district = districtRepository.findById(districtId);
		}
		else {
			district = districtRepository.findBydistrict(districts);
		}
		district.setDistrict(districtEditRequest.getDistrict());
		district.setArea(districtEditRequest.getArea());
		State state = stateRepository.findOne(districtEditRequest.getStateId());
		district.setState(state);
		district.setPopulation(districtEditRequest.getPopulation());
		district.setHeadquater(districtEditRequest.getHeadquater());
		districtRepository.save(district);
		return district;

	}

}
