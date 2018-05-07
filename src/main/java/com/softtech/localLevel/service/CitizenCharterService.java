package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.request.CitizenCharterCreationRequest;
import com.softech.localLevel.request.CitizenCharterEditRequest;
import com.softtech.localLevel.model.CitizenCharter;
import com.softtech.localLevel.repository.CitizenCharterRepository;
import com.softtech.localLevel.response.CitizenCharterResponse;

@Service
public class CitizenCharterService {

	@Autowired
	private CitizenCharterRepository citizenCharterRepository;

	@Transactional
	public void postCitizenCharter(CitizenCharterCreationRequest CitizenCharterCreationRequest) {

		CitizenCharter cz = new CitizenCharter();
		cz.setComplainTo(CitizenCharterCreationRequest.getComplainTo());
		cz.setPrice(CitizenCharterCreationRequest.getPrice());
		cz.setRemarks(CitizenCharterCreationRequest.getRemarks());
		cz.setResponsiblePerson(CitizenCharterCreationRequest.getResponsiblePerson());
		cz.setServiceType(CitizenCharterCreationRequest.getServiceType());
		cz.setServiceRequirement(CitizenCharterCreationRequest.getServiceRequirement());
		cz.setTime(CitizenCharterCreationRequest.getTime());
		citizenCharterRepository.save(cz);

	}

	@Transactional
	public List<CitizenCharterResponse> getCitizenCharter() {
		List<CitizenCharter> cs = citizenCharterRepository.findAll();
		List<CitizenCharterResponse> citizenCharterResponseList = new ArrayList<CitizenCharterResponse>();

		cs.stream().forEach(u -> {
			CitizenCharterResponse csr = new CitizenCharterResponse();
			csr.setId(u.getId());
			csr.setComplainTo(u.getComplainTo());
			csr.setPrice(u.getPrice());
			csr.setRemarks(u.getRemarks());
			csr.setServiceRequirement(u.getServiceRequirement());
			csr.setResponsiblePerson(u.getResponsiblePerson());
			csr.setServiceType(u.getServiceType());
			csr.setTime(u.getTime());
			citizenCharterResponseList.add(csr);

		});
		return citizenCharterResponseList;
	}

	@Transactional
	public void editCitizenCharter(Long id, CitizenCharterEditRequest citizenCharterEditRequest) {

		CitizenCharter cz = citizenCharterRepository.findById(id);
		cz.setComplainTo(citizenCharterEditRequest.getComplainTo());
		cz.setPrice(citizenCharterEditRequest.getPrice());

		cz.setRemarks(citizenCharterEditRequest.getRemarks());
		cz.setResponsiblePerson(citizenCharterEditRequest.getResponsiblePerson());
		cz.setServiceType(citizenCharterEditRequest.getServiceType());
		cz.setServiceRequirement(citizenCharterEditRequest.getServiceRequirement());
		cz.setTime(citizenCharterEditRequest.getTime());
		citizenCharterRepository.save(cz);

	}

	@Transactional
	public void deleteCitizenCharter(Long id) {
		// TODO Auto-generated method stub
		CitizenCharter cz = citizenCharterRepository.findById(id);
		citizenCharterRepository.delete(cz);
		
	}

}
