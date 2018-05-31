package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softech.localLevel.request.CitizenCharterCreationRequest;
import com.softech.localLevel.request.CitizenCharterEditRequest;
import com.softtech.localLevel.model.CitizenCharter;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Metropolitan;
import com.softtech.localLevel.model.Municipality;
import com.softtech.localLevel.model.RuralMunicipality;
import com.softtech.localLevel.model.SubMetropolitan;
import com.softtech.localLevel.repository.CitizenCharterRepository;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.MetropolitanRepository;
import com.softtech.localLevel.repository.MunicipalityRepository;
import com.softtech.localLevel.repository.RuralMunicipalityRepository;
import com.softtech.localLevel.repository.SubMetropolitanRepository;
import com.softtech.localLevel.response.CitizenCharterResponse;
import com.softtech.localLevel.util.Status;

@Service
public class CitizenCharterService {

	@Autowired
	private CitizenCharterRepository citizenCharterRepository;

	@Autowired
	private RuralMunicipalityRepository ruralMunicipalityRepository;

	@Autowired
	private MunicipalityRepository municipalityRepository;

	@Autowired
	private MetropolitanRepository metropolitanRepository;

	@Autowired
	private SubMetropolitanRepository subMetropolitanRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Transactional
	public void postCitizenCharter(CitizenCharterCreationRequest CitizenCharterCreationRequest, String metropolitan,
			String municipality, String subMetropolitan, String ruralMunicipality) {

		CitizenCharter cz = new CitizenCharter();
		cz.setComplainTo(CitizenCharterCreationRequest.getComplainTo());
		cz.setPrice(CitizenCharterCreationRequest.getPrice());
		cz.setRemarks(CitizenCharterCreationRequest.getRemarks());
		cz.setResponsiblePerson(CitizenCharterCreationRequest.getResponsiblePerson());
		cz.setServiceType(CitizenCharterCreationRequest.getServiceType());
		cz.setServiceRequirement(CitizenCharterCreationRequest.getServiceRequirement());
		cz.setTime(CitizenCharterCreationRequest.getTime());

		if (ruralMunicipality != null) {

			RuralMunicipality rm = ruralMunicipalityRepository.findByRuralMunicipalAndStatusNot(ruralMunicipality,
					Status.DELETED);
			if (rm == null) {
				throw new NotFoundException("Not found!");
			}
			CitizenCharter cr=citizenCharterRepository.findByRuralMunicipalityIdAndStatusNot(rm.getId(),Status.DELETED);
			if(cr!=null) {
				throw new AlreadyExistException("Citizen Charter Already exists for this Rural Municipality");
			}
			
			cz.setDistrictId(rm.getDistrict().getId());
			cz.setRuralMunicipalityId(rm.getId());
			cz.setMunicipalityId((long) 0);
			cz.setMetropolitanId((long) 0);
			cz.setSubMetropolitanId((long) 0);
		}

		else if (municipality != null) {
			Municipality rm = municipalityRepository.findByMunicipalAndStatusNot(municipality, Status.DELETED);
			if (rm == null) {
				throw new NotFoundException("Not found!");
			}
			CitizenCharter cr=citizenCharterRepository.findByMunicipalityIdAndStatusNot(rm.getId(),Status.DELETED);
			if(cr!=null) {
				throw new AlreadyExistException("Citizen Charter Already exists for this Municipality");
			}
			cz.setDistrictId(rm.getDistrict().getId());
			cz.setMunicipalityId(rm.getId());
			cz.setRuralMunicipalityId((long) 0);
			cz.setMetropolitanId((long) 0);
			cz.setSubMetropolitanId((long) 0);

		} else if (metropolitan != null) {
			Metropolitan rm = metropolitanRepository.findByMetropolitanAndStatusNot(metropolitan, Status.DELETED);
			if (rm == null) {
				throw new NotFoundException("Not found!");
			}
			CitizenCharter cr=citizenCharterRepository.findByMetropolitanIdAndStatusNot(rm.getId(),Status.DELETED);
			if(cr!=null) {
				throw new AlreadyExistException("Citizen Charter Already exists for this Metropolitan");
			}
			cz.setDistrictId(rm.getDistrict().getId());
			cz.setMetropolitanId(rm.getId());
			cz.setRuralMunicipalityId((long) 0);
			cz.setMunicipalityId((long) 0);
			cz.setSubMetropolitanId((long) 0);

		} else {

			SubMetropolitan rm = subMetropolitanRepository.findBySubMetropolitanAndStatusNot(subMetropolitan,
					Status.DELETED);
			if (rm == null) {
				throw new NotFoundException("Not found!");
			}
			CitizenCharter cr=citizenCharterRepository.findBySubMetropolitanIdAndStatusNot(rm.getId(),Status.DELETED);
			if(cr!=null) {
				throw new AlreadyExistException("Citizen Charter Already exists for this Submetropolitan");
			}
			cz.setDistrictId(rm.getDistrict().getId());
			cz.setSubMetropolitanId(rm.getId());
			cz.setRuralMunicipalityId((long) 0);
			cz.setMunicipalityId((long) 0);
			cz.setMetropolitanId((long) 0);

		}

		cz.setStatus(Status.ACTIVE);
		citizenCharterRepository.save(cz);

	}

	@Transactional
	public CitizenCharterResponse getCitizenCharter(String district, String metropolitan, String subMetropolitan,
			String municipality, String ruralMunicipality) {
		// List<CitizenCharter> cs = citizenCharterRepository.findAll();
		// List<CitizenCharterResponse> citizenCharterResponseList = new
		// ArrayList<CitizenCharterResponse>();

		// cs.stream().forEach(u -> {
		// CitizenCharterResponse csr = new CitizenCharterResponse();
		// csr.setId(u.getId());
		// csr.setComplainTo(u.getComplainTo());
		// csr.setPrice(u.getPrice());
		// csr.setRemarks(u.getRemarks());
		// csr.setServiceRequirement(u.getServiceRequirement());
		// csr.setResponsiblePerson(u.getResponsiblePerson());
		// csr.setServiceType(u.getServiceType());
		// csr.setTime(u.getTime());
		// citizenCharterResponseList.add(csr);
		//
		//
		// });
		CitizenCharterResponse ccr=new CitizenCharterResponse();
		if (ruralMunicipality != null) {
			RuralMunicipality rm = ruralMunicipalityRepository.findByRuralMunicipalAndStatusNot(ruralMunicipality,
					Status.DELETED);
			if (rm == null) {
				throw new NotFoundException("Rural Municipality with name "+rm.getRuralMunicipal()+" not found.");
			}
			//System.out.println("Rm id:"+rm.getId());
			CitizenCharter cc = citizenCharterRepository.findByRuralMunicipalityIdAndStatusNot(rm.getId(),Status.DELETED);
			if(cc==null) {
				throw new NotFoundException("Citizen Charter not found.");
			}
			System.out.println("complaint "+cc.getComplainTo());
			ccr.setComplainTo(cc.getComplainTo());
			District districts=districtRepository.findByIdAndStatusNot(cc.getDistrictId(),Status.DELETED);
			ccr.setDistrict(districts.getDistrict());
			ccr.setPrice(cc.getPrice());
			ccr.setRemarks(cc.getRemarks());
			ccr.setResponsiblePerson(cc.getResponsiblePerson());
			ccr.setServiceRequirement(cc.getServiceRequirement());
			ccr.setServiceType(cc.getServiceType());
			ccr.setTime(cc.getTime());
			RuralMunicipality rms=ruralMunicipalityRepository.findByIdAndStatusNot(cc.getRuralMunicipalityId(),Status.DELETED);
			ccr.setRuralMunicipality(rms.getRuralMunicipal());
			
			
		}
		return ccr;
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
