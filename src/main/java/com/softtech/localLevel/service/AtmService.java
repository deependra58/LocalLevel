package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.request.AtmCreationRequest;
import com.softtech.localLevel.model.Atm;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.AtmRepository;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.AtmResponse;
import com.softtech.localLevel.util.Status;

@Service
public class AtmService {
	@Autowired
	private AtmRepository atmRepository;

	@Autowired
	private DistrictRepository districtRespository;

	@Autowired
	private StateRepository stateRepository;

	@Transactional
	public void postAtm(AtmCreationRequest atmCreationRequest) {

		Atm atm = new Atm();
		atm.setAtmName(atmCreationRequest.getAtmName());
		atm.setLocalAddress(atmCreationRequest.getLocalAddress());
		District district = districtRespository.findBydistrict(atmCreationRequest.getDistrict());
		atm.setDistrict(district);
		State state = stateRepository.findById(district.getState().getId());
		atm.setState(state);
		atm.setStatus(Status.ACTIVE);
		atmRepository.save(atm);

	}

	@Transactional
	public List<AtmResponse> getAllAtm(String states) {
		State state = stateRepository.findByState(states);
		List<Atm> atm = atmRepository.findAllByStateAndStatusNot(new State(state.getId()), Status.DELETED);
		List<AtmResponse> atmResponseList = new ArrayList<AtmResponse>();
		atm.stream().forEach(u -> {

			AtmResponse atmResponse = new AtmResponse();
			atmResponse.setAtmName(u.getAtmName());
			atmResponse.setLocalAddress(u.getLocalAddress());
			District district = districtRespository.findById(u.getDistrict().getId());
			atmResponse.setDistrict(district.getDistrict());
			atmResponseList.add(atmResponse);

		});
		return atmResponseList;
	}

}
