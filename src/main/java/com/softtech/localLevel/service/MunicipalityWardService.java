package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.NotFoundException;
import com.softech.localLevel.request.MunicipalityWardCreationRequest;
import com.softech.localLevel.request.MunicipalityWardEditRequest;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.MunicipalWard;
import com.softtech.localLevel.model.Municipality;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.MunicipalityRepository;
import com.softtech.localLevel.repository.MunicipalityWardRepository;
import com.softtech.localLevel.response.MunicipalityWardResponse;
import com.softtech.localLevel.util.Status;

/*<<This is the service implementation for Municipality ward>>
 * @Author Deependra
 * @Since 10 May, 2018
 * @Version 1.0.1*/
@Service
public class MunicipalityWardService {

	@Autowired
	private MunicipalityWardRepository municipalityWardRepository;

	@Autowired
	private MunicipalityRepository municipalityRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Transactional
	public void postMunicipalityWard(MunicipalityWardCreationRequest municipalityWardCreationRequest) {
		MunicipalWard rmw = new MunicipalWard();

		Municipality municipality = municipalityRepository
				.findByMunicipalAndStatusNot(municipalityWardCreationRequest.getMunicipality(), Status.DELETED);

		if (municipality == null) {
			throw new NotFoundException(
					"Municipality with name " + municipalityWardCreationRequest.getMunicipality() + " not found!");
		}

		rmw.setDistrictId(municipality.getDistrict().getId());
		rmw.setMunicipality(municipality);
		rmw.setNewWardId(municipalityWardCreationRequest.getNewWardId());
		rmw.setNewWardName(municipalityWardCreationRequest.getNewWardName());
		rmw.setOldWardId(municipalityWardCreationRequest.getOldWardId());
		rmw.setOldVdcName(municipalityWardCreationRequest.getOldVdcName());
		rmw.setOldWardName(municipalityWardCreationRequest.getOldWardName());
		rmw.setStatus(Status.ACTIVE);
		municipalityWardRepository.save(rmw);

	}

	@Transactional
	public List<MunicipalityWardResponse> getOldVdc(String municipality, Long newWardId) {
		List<MunicipalityWardResponse> rmwr = new ArrayList<MunicipalityWardResponse>();
		Municipality municipal = municipalityRepository.findByMunicipalAndStatusNot(municipality, Status.DELETED);
		if (municipal == null) {
			throw new NotFoundException("Municipality with name " + municipality + " not found!");
		}
		List<MunicipalWard> rm = municipalityWardRepository.findAllByMunicipalityAndNewWardIdAndStatusNot(municipal,
				newWardId, Status.DELETED);
		rm.stream().forEach(u -> {
			MunicipalityWardResponse rmr = new MunicipalityWardResponse();
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
	public MunicipalityWardResponse getNewWard(String oldVdcName, Long oldWardId) {
		MunicipalityWardResponse rmwr = new MunicipalityWardResponse();
		MunicipalWard rmw = municipalityWardRepository.findByOldVdcNameAndOldWardIdAndStatusNot(oldVdcName, oldWardId,
				Status.DELETED);
		if (rmw == null) {
			throw new NotFoundException("Not found!!");
		}
		Municipality muni = municipalityRepository.findByIdAndStatusNot(rmw.getMunicipality().getId(), Status.DELETED);
		rmwr.setMunicipalityName(muni.getMunicipal());
		rmwr.setNewWardId(rmw.getNewWardId());
		rmwr.setNewWardName(rmw.getNewWardName());
		return rmwr;
	}

	@Transactional
	public void editMunicipalityWard(Long id, MunicipalityWardEditRequest municipalityWardEditRequest) {
		MunicipalWard rmw = municipalityWardRepository.findByIdAndStatusNot(id, Status.DELETED);
		if (rmw == null) {
			throw new NotFoundException("MunicipalityWard with id:" + id + " not found!");
		}

		Municipality municipal = municipalityRepository
				.findByMunicipalAndStatusNot(municipalityWardEditRequest.getMunicipality(), Status.DELETED);
		if (municipal == null) {
			throw new NotFoundException(
					"Municipality with name " + municipalityWardEditRequest.getMunicipality() + " not found.");
		}
		rmw.setDistrictId(municipal.getDistrict().getId());
		rmw.setMunicipality(municipal);
		rmw.setNewWardId(municipalityWardEditRequest.getNewWardId());
		rmw.setNewWardName(municipalityWardEditRequest.getNewWardName());
		rmw.setOldWardId(municipalityWardEditRequest.getOldWardId());
		rmw.setOldVdcName(municipalityWardEditRequest.getOldVdcName());
		rmw.setOldWardName(municipalityWardEditRequest.getOldWardName());
		rmw.setStatus(Status.ACTIVE);
		municipalityWardRepository.save(rmw);
	}

	@Transactional
	public void deleteInfo(Long id) {
		MunicipalWard rmw = municipalityWardRepository.findByIdAndStatusNot(id, Status.DELETED);
		if (rmw == null) {
			throw new NotFoundException("MunicipalityWard with id:" + id + " not found!");
		}
		rmw.setStatus(Status.DELETED);
		municipalityWardRepository.save(rmw);
	}

}
