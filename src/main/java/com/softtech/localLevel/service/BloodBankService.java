package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.request.BloodBankCreationRequest;
import com.softtech.localLevel.model.BloodBank;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.BloodBankRepository;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.BloodBankResponse;
import com.softtech.localLevel.util.Status;

@Service
public class BloodBankService {

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private BloodBankRepository bloodBankRepository;

	@Transactional
	public void postBloodBank(BloodBankCreationRequest bloodBankCreationRequest) {

		BloodBank bb = new BloodBank();
		bb.setBloodBankName(bloodBankCreationRequest.getBloodBankName());
		bb.setLocalAddress(bloodBankCreationRequest.getLocalAddress());
		District district = districtRepository.findByDistrict(bloodBankCreationRequest.getDistrict());
		bb.setDistrict(district);
		State state = stateRepository.findById(district.getState().getId());
		bb.setState(state);
		bb.setStatus(Status.ACTIVE);
		bb.setContactNo(bloodBankCreationRequest.getContactNo());
		bloodBankRepository.save(bb);

	}

	@Transactional
	public List<BloodBankResponse> getAllBloodBank(String states) {

		State state = stateRepository.findByState(states);
		//System.out.println("search by state."+state.getState());
		List<BloodBank> bb = bloodBankRepository.findAllByStateAndStatusNot(new State(state.getId()), Status.DELETED);
		List<BloodBankResponse> bbList = new ArrayList<BloodBankResponse>();
		bb.stream().forEach(u -> {
			BloodBankResponse bloodBankResponse = new BloodBankResponse();
			bloodBankResponse.setBloodBankName(u.getBloodBankName());
			bloodBankResponse.setContactNo(u.getContactNo());
			District district = districtRepository.findById(u.getDistrict().getId());
			bloodBankResponse.setDistrict(district.getDistrict());
			bloodBankResponse.setLocalAddress(u.getLocalAddress());

			bbList.add(bloodBankResponse);

		});
		return bbList;
	}

}


