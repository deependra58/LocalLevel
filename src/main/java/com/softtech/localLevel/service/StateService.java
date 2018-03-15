package com.softtech.localLevel.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softtech.localLevel.dto.DistrictDto;
import com.softtech.localLevel.dto.StateDetailDto;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.util.Base64Util;
import com.softtech.localLevel.util.FileUtil;

@Service
public class StateService {

	private static final Logger LOG = LoggerFactory.getLogger(StateService.class);
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private DistrictRepository districtRepository;

	@Transactional
	public StateDetailDto getStateDetails(String state) {
		LOG.info("\n\nRequested accepted to show state details from state name\n");
		StateDetailDto stateDetailDto = new StateDetailDto();
		List<DistrictDto> districtDtoList = new ArrayList<DistrictDto>();
		State states = stateRepository.findByState(state);
		System.out.println("/n/nWebsite:"+states.getWebsite());
		stateDetailDto.setWebsite(states.getWebsite());
		
		stateDetailDto.setArea(states.getArea());
		stateDetailDto.setPopulation(states.getPopulation());
		stateDetailDto.setMayor(states.getMayor());
		stateDetailDto.setCapital(states.getCapital());
		stateDetailDto.setDeputMayor(states.getDeputMayor());
		stateDetailDto.setDensity(states.getDensity());
		List<District> districts = districtRepository.findAllByState(new State(states.getId()));
		districts.stream().forEach(u -> {

			DistrictDto district = new DistrictDto();
			district.setDistrict(u.getDistrict());
			districtDtoList.add(district);
			stateDetailDto.setDistrict(districtDtoList);

		});
		if (states.getStatePicture() != null) {
			File file = new File(states.getStatePicture());
			try {
				stateDetailDto
						.setStatePicture(Base64Util.encodeFileToBase64Binary(file));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				file.deleteOnExit();
			}
		}

		return stateDetailDto;
	}

	public void postPicture(String string,Long stateId) {
		File file = null;
		try {
			State state = stateRepository.findById(stateId);
			
			LOG.info("Request Accepted to post state picture");
			file = FileUtil.write(String.valueOf(new Date().getTime()).concat(".").concat("png"), string);
			LOG.info("path {}", file.getAbsolutePath());
			if (file != null)
				state.setStatePicture(file.getAbsolutePath());
			stateRepository.save(state);

		} catch (Exception e) {
			e.printStackTrace();
			if (file != null) {
				file.delete();
			}
		}
	}

}
