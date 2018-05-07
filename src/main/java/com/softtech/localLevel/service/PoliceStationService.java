package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.request.PoliceStationCreationRequest;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.PoliceStation;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.PoliceStationRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.PoliceStationResponse;
import com.softtech.localLevel.util.Status;

@Service
public class PoliceStationService {

	@Autowired
	private PoliceStationRepository policeStationRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private DistrictRepository districtRepository;

	public void postPoliceStation(PoliceStationCreationRequest policeStationCreationRequest) {

		PoliceStation ps = new PoliceStation();
		ps.setPoliceStationName(policeStationCreationRequest.getPoliceStationName());
		ps.setLocalAddress(policeStationCreationRequest.getContactNo());
		ps.setContactNo(policeStationCreationRequest.getContactNo());
		District district = districtRepository.findBydistrict(policeStationCreationRequest.getDistrict());
		ps.setDistrict(district);
		State state = stateRepository.findById(district.getState().getId());
		ps.setState(state);
		ps.setStatus(Status.ACTIVE);
		policeStationRepository.save(ps);

	}

	@Transactional
	public List<PoliceStationResponse> listAllPoliceStation(String states) {

		List<PoliceStationResponse> policeStationResponse = new ArrayList<PoliceStationResponse>();
		State state = stateRepository.findByState(states);
		List<PoliceStation> policeStation = policeStationRepository.findAllByStateAndStatusNot(new State(state.getId()),
				Status.DELETED);
		policeStation.stream().forEach(u -> {
			PoliceStationResponse psr = new PoliceStationResponse();
			psr.setPoliceStationName(u.getPoliceStationName());
			psr.setLocalAddress(u.getLocalAddress());
			psr.setContactNo(u.getContactNo());
			District district = districtRepository.findById(u.getDistrict().getId());
			psr.setDistrict(district.getDistrict());
			policeStationResponse.add(psr);
		});

		return policeStationResponse;
	}

}
