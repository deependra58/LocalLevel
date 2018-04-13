package com.softtech.localLevel.service;

import static org.mockito.Matchers.longThat;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.hssf.record.ContinueRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Metropolitan;
import com.softtech.localLevel.model.Municipality;
import com.softtech.localLevel.model.RuralMunicipality;
import com.softtech.localLevel.model.SubMetropolitan;
import com.softtech.localLevel.model.Vdc;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.MetropolitanRepository;
import com.softtech.localLevel.repository.MunicipalityRepository;
import com.softtech.localLevel.repository.RuralMunicipalityRepository;
import com.softtech.localLevel.repository.SubMetropolitanRepository;
import com.softtech.localLevel.repository.VdcRepository;
import com.softtech.localLevel.response.OldVdcListResponse;
import com.softtech.localLevel.response.VdcResponse;
import com.softtech.localLevel.util.Status;

@Service
public class VdcService {

	@Autowired
	private VdcRepository vdcRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private RuralMunicipalityRepository ruralMunicipalityRepository;

	@Autowired
	private MunicipalityRepository municipalityRepository;

	@Autowired
	private SubMetropolitanRepository subMetropolitanRepository;

	@Autowired
	private MetropolitanRepository metropolitanRepository;

	@Transactional
	public void postVdcDetails(String vdc, String district, String ruralMunicipality, String municipality,
			String subMetropolitan, String metropolitan) {
		Vdc vdcs = new Vdc();
		vdcs.setVdc(vdc);
		District districts = districtRepository.findByDistrict(district);
		if (districts == null) {
			throw new NotFoundException("District with name " + district + " not found. Try with different name.");
		}
		vdcs.setDistrict(districts);

		if (ruralMunicipality != null) {
			RuralMunicipality ruralMunicipalities = ruralMunicipalityRepository
					.findByRuralMunicipalAndStatusNot(ruralMunicipality, Status.DELETED);
			if (ruralMunicipalities == null) {
				throw new NotFoundException(
						"Rural Municipality with name " + ruralMunicipality + " not found. Try with different name.");
			}
			vdcs.setRuralMuniciaplity_id(ruralMunicipalities.getId());

		}
		if (municipality != null) {
			Municipality municipalities = municipalityRepository.findByMunicipalAndStatusNot(municipality,
					Status.DELETED);
			if (municipalities == null) {
				throw new NotFoundException(
						"Municipality with name " + municipality + " not found. Try with different name.");
			}
			vdcs.setMunicipality_id(municipalities.getId());
		}

		if (subMetropolitan != null) {
			SubMetropolitan subMetropolitans = subMetropolitanRepository
					.findBySubMetropolitanAndStatusNot(subMetropolitan, Status.DELETED);
			if (subMetropolitans == null) {
				throw new NotFoundException(
						"Sub-metropolitan with name " + subMetropolitan + " not found. Try with different name.");
			}
			vdcs.setSubmetropolitan_id(subMetropolitans.getId());
		}

		if (metropolitan != null) {
			Metropolitan metropolitans = metropolitanRepository.findByMetropolitanAndStatusNot(metropolitan,
					Status.DELETED);
			if (metropolitans == null) {
				throw new NotFoundException(
						"Metropolitan with name " + metropolitan + " not found. Try with different name.");
			}
			vdcs.setMetropolitan_id(metropolitans.getId());

		}

		vdcs.setStatus(Status.ACTIVE);
		vdcRepository.save(vdcs);

	}

	@Transactional
	public void editVdcDetails(Long vdcId, String vdc, String district, String ruralMunicipality, String municipality,
			String subMetropolitan, String metropolitan) {
		Vdc vdcs = vdcRepository.findByIdAndStatusNot(vdcId, Status.DELETED);

		vdcs.setVdc(vdc);
		vdcs.setRuralMuniciaplity_id(null);
		vdcs.setMunicipality_id(null);
		vdcs.setSubmetropolitan_id(null);
		vdcs.setMetropolitan_id(null);
		District districts = districtRepository.findByDistrict(district);
		if (districts == null) {
			throw new NotFoundException("District with name " + district + " not found. Try with different name.");
		}
		vdcs.setDistrict(districts);

		if (ruralMunicipality != null) {
			RuralMunicipality ruralMunicipalities = ruralMunicipalityRepository
					.findByRuralMunicipalAndStatusNot(ruralMunicipality, Status.DELETED);
			if (ruralMunicipalities == null) {
				throw new NotFoundException(
						"Rural Municipality with name " + ruralMunicipality + " not found. Try with different name.");
			}
			vdcs.setRuralMuniciaplity_id(ruralMunicipalities.getId());

		}
		if (municipality != null) {
			Municipality municipalities = municipalityRepository.findByMunicipalAndStatusNot(municipality,
					Status.DELETED);
			if (municipalities == null) {
				throw new NotFoundException(
						"Municipality with name " + municipality + " not found. Try with different name.");
			}
			vdcs.setMunicipality_id(municipalities.getId());
		}

		if (subMetropolitan != null) {
			SubMetropolitan subMetropolitans = subMetropolitanRepository
					.findBySubMetropolitanAndStatusNot(subMetropolitan, Status.DELETED);
			if (subMetropolitans == null) {
				throw new NotFoundException(
						"Sub-metropolitan with name " + subMetropolitan + " not found. Try with different name.");
			}
			vdcs.setSubmetropolitan_id(subMetropolitans.getId());
		}

		if (metropolitan != null) {
			Metropolitan metropolitans = metropolitanRepository.findByMetropolitanAndStatusNot(metropolitan,
					Status.DELETED);
			if (metropolitans == null) {
				throw new NotFoundException(
						"Metropolitan with name " + metropolitan + " not found. Try with different name.");
			}
			vdcs.setMetropolitan_id(metropolitans.getId());

		}

		vdcs.setStatus(Status.ACTIVE);
		vdcRepository.save(vdcs);

	}

	@Transactional
	public void deleteVdc(Long vdcId, String vdc) {
		Vdc vdcs = vdcRepository.findByIdAndVdcAndStatusNot(vdcId, vdc, Status.DELETED);
		vdcs.setStatus(Status.DELETED);

	}

	@Transactional
	public List<VdcResponse> getNewVdcs(String oldVdc) {
		List<Vdc> vdc = vdcRepository.findAllByVdcAndStatusNot(oldVdc, Status.DELETED);
		List<VdcResponse> vdcResponseList = new ArrayList<VdcResponse>();
		if (vdc.equals(null)) {
			throw new NotFoundException("Vdc with the given name not found");

		}

		vdc.stream().forEach(u -> {

			VdcResponse vdcResponse = new VdcResponse();

			if (u.getRuralMuniciaplity_id() != 0) {
				RuralMunicipality rMunicipality = ruralMunicipalityRepository.findById(u.getRuralMuniciaplity_id());
				vdcResponse.setNewVdc(rMunicipality.getRuralMunicipal());
				vdcResponse.setLocalLevelType(rMunicipality.getLocalLevelType());
				vdcResponseList.add(vdcResponse);

			}

			if (u.getMunicipality_id() != 0) {

				Municipality municipality = municipalityRepository.findById(u.getMunicipality_id());
				vdcResponse.setNewVdc(municipality.getMunicipal());
				vdcResponse.setLocalLevelType(municipality.getLocalLevelType());
				vdcResponseList.add(vdcResponse);
			}

			if (u.getMetropolitan_id() != 0) {

				Metropolitan metropolitan = metropolitanRepository.findById(u.getMetropolitan_id());
				vdcResponse.setNewVdc(metropolitan.getMetropolitan());
				vdcResponse.setLocalLevelType(metropolitan.getLocalLevelType());
				vdcResponseList.add(vdcResponse);
			}

			if (u.getSubmetropolitan_id() != 0) {

				SubMetropolitan subMetropolitan = subMetropolitanRepository.findById(u.getSubmetropolitan_id());
				vdcResponse.setNewVdc(subMetropolitan.getSubMetropolitan());
				vdcResponse.setLocalLevelType(subMetropolitan.getLocalLevelType());
				vdcResponseList.add(vdcResponse);
			}
		});

		return vdcResponseList;

	}

	@Transactional
	public List<OldVdcListResponse> getOldVdcList(String district) {

		District districts = districtRepository.findByDistrict(district);
		List<Vdc> vdcs = vdcRepository.findAllByDistrict(new District(districts.getId()));
		List<OldVdcListResponse> oldVdcListResponses = new ArrayList<OldVdcListResponse>();
		vdcs.stream().forEach(u -> {
			OldVdcListResponse oldVdcResponse = new OldVdcListResponse();
			oldVdcResponse.setOldVdc(u.getVdc());
			oldVdcListResponses.add(oldVdcResponse);

		});

		return oldVdcListResponses;
	}

}
