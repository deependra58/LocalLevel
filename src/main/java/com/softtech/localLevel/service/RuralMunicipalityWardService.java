package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.NotFoundException;
import com.softech.localLevel.request.RuralMunicipalityWardCreationRequest;
import com.softech.localLevel.request.RuralMunicipalityWardEditRequest;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.RuralMunicipalWard;
import com.softtech.localLevel.model.RuralMunicipality;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.RuralMunicipalityRepository;
import com.softtech.localLevel.repository.RuralMunicipalityWardRepository;
import com.softtech.localLevel.response.RuralMunicipalWardResponse;
import com.softtech.localLevel.util.Status;

/**
 * <<This is the service of RuralMunicipality ward>>
 * 
 * @Author Deependra
 * @Since 9 May 2018
 * @version 1.0.1
 */

@Service
public class RuralMunicipalityWardService {

	@Autowired
	private RuralMunicipalityWardRepository ruralMunicipalityWardRepository;

	@Autowired
	private RuralMunicipalityRepository ruralMunicipalityRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Transactional
	public void postRuralMunicipalityWard(RuralMunicipalityWardCreationRequest ruralMunicipalityWardCreationRequest) {
		RuralMunicipalWard rmw = new RuralMunicipalWard();

		RuralMunicipality ruralMunicipality = ruralMunicipalityRepository.findByRuralMunicipalAndStatusNot(
				ruralMunicipalityWardCreationRequest.getRuralMunicipality(), Status.DELETED);

		if (ruralMunicipality == null) {
			throw new NotFoundException("Rural Municipality with name not found!");
		}

		rmw.setDistrictId(ruralMunicipality.getDistrict().getId());
		rmw.setRuralMunicipality(ruralMunicipality);
		rmw.setNewWardId(ruralMunicipalityWardCreationRequest.getNewWardId());
		rmw.setNewWardName(ruralMunicipalityWardCreationRequest.getNewWardName());
		rmw.setOldWardId(ruralMunicipalityWardCreationRequest.getOldWardId());
		rmw.setOldVdcName(ruralMunicipalityWardCreationRequest.getOldVdcName());
		rmw.setOldWardName(ruralMunicipalityWardCreationRequest.getOldWardName());
		rmw.setStatus(Status.ACTIVE);
		ruralMunicipalityWardRepository.save(rmw);

	}

	@Transactional
	public List<RuralMunicipalWardResponse> getOldVdc(String ruralMunicipality, Long newWardId) {
		List<RuralMunicipalWardResponse> rmwr = new ArrayList<RuralMunicipalWardResponse>();
		RuralMunicipality ruralMunicipal = ruralMunicipalityRepository
				.findByRuralMunicipalAndStatusNot(ruralMunicipality, Status.DELETED);
		if(ruralMunicipal==null) {
			throw new NotFoundException("Rural Municipality with name "+ruralMunicipality+" not found.");
		}
		List<RuralMunicipalWard> rm = ruralMunicipalityWardRepository
				.findAllByRuralMunicipalityAndNewWardIdAndStatusNot(ruralMunicipal, newWardId, Status.DELETED);
		rm.stream().forEach(u -> {
			RuralMunicipalWardResponse rmr = new RuralMunicipalWardResponse();
			District district = districtRepository.findById(u.getDistrictId());
			rmr.setDistrict(district.getDistrict());
			rmr.setId(u.getId());
			rmr.setOldWardId(u.getOldWardId());
			rmr.setOldVdcName(u.getOldVdcName());
			rmr.setOldWardName(u.getOldWardName());

			rmwr.add(rmr);

		});
		return rmwr;
	}

	@Transactional
	public RuralMunicipalWardResponse getOldVdcInfo(String oldVdcName, Long oldWardId) {
		RuralMunicipalWardResponse rmwr = new RuralMunicipalWardResponse();
		RuralMunicipalWard rmw = ruralMunicipalityWardRepository.findByOldVdcNameAndOldWardIdAndStatusNot(oldVdcName,
				oldWardId, Status.DELETED);
		if (rmw == null) {
			throw new NotFoundException("Not found!!");
		}
		RuralMunicipality ruralMuni = ruralMunicipalityRepository
				.findByIdAndStatusNot(rmw.getRuralMunicipality().getId(), Status.DELETED);
		rmwr.setRuralMunicipalityName(ruralMuni.getRuralMunicipal());
		rmwr.setNewWardId(rmw.getNewWardId());
		rmwr.setNewWardName(rmw.getNewWardName());
		return rmwr;
	}

	@Transactional
	public void editRuralMunicipalityWard(Long id, RuralMunicipalityWardEditRequest ruralMunicipalityWardEditRequest) {

		RuralMunicipalWard rmw = ruralMunicipalityWardRepository.findByIdAndStatusNot(id, Status.DELETED);
		if (rmw == null) {
			throw new NotFoundException("RuralMunicipalityWard with id:" + id + " not found!");
		}

		RuralMunicipality ruralMunicipal = ruralMunicipalityRepository.findByRuralMunicipalAndStatusNot(
				ruralMunicipalityWardEditRequest.getRuralMunicipality(), Status.DELETED);
		if (ruralMunicipal == null) {
			throw new NotFoundException("RuralMunicipality with name "
					+ ruralMunicipalityWardEditRequest.getRuralMunicipality() + " not found.");
		}
		rmw.setDistrictId(ruralMunicipal.getDistrict().getId());
		rmw.setRuralMunicipality(ruralMunicipal);
		rmw.setNewWardId(ruralMunicipalityWardEditRequest.getNewWardId());
		rmw.setNewWardName(ruralMunicipalityWardEditRequest.getNewWardName());
		rmw.setOldWardId(ruralMunicipalityWardEditRequest.getOldWardId());
		rmw.setOldVdcName(ruralMunicipalityWardEditRequest.getOldVdcName());
		rmw.setOldWardName(ruralMunicipalityWardEditRequest.getOldWardName());
		rmw.setStatus(Status.ACTIVE);
		ruralMunicipalityWardRepository.save(rmw);

	}

	@Transactional
	public void deleteInfo(Long id) {
		RuralMunicipalWard rmw = ruralMunicipalityWardRepository.findByIdAndStatusNot(id, Status.DELETED);
		if (rmw == null) {
			throw new NotFoundException("RuralMunicipalityWard with id:" + id + " not found!");
		}
		rmw.setStatus(Status.DELETED);
		ruralMunicipalityWardRepository.save(rmw);
		
	}

}
