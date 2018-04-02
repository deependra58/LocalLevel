package com.softtech.localLevel.service;

import javax.transaction.Transactional;

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
		if(districts==null) {
			throw new NotFoundException("District with name "+district+" not found. Try with different name.");
		}
		vdcs.setDistrict(districts);

		if (ruralMunicipality != null) {
			RuralMunicipality ruralMunicipalities = ruralMunicipalityRepository.findByRuralMunicipal(ruralMunicipality);
			if(ruralMunicipalities==null) {
				throw new NotFoundException("Rural Municipality with name "+ruralMunicipality+" not found. Try with different name.");
			}
			vdcs.setRuralMunicipality(ruralMunicipalities);

		}
		if (municipality != null) {
			Municipality municipalities = municipalityRepository.findByMunicipal(municipality);
			if(municipalities==null) {
				throw new NotFoundException("Municipality with name "+ municipality+" not found. Try with different name.");
			}
			vdcs.setMunicipality(municipalities);
		}

		if (subMetropolitan != null) {
			SubMetropolitan subMetropolitans = subMetropolitanRepository.findBySubMetropolitan(subMetropolitan);
			if(subMetropolitans==null) {
				throw new NotFoundException("Sub-metropolitan with name "+ subMetropolitan +" not found. Try with different name.");
			}
			vdcs.setSubMetropolitan(subMetropolitans);
		}

		if (metropolitan != null) {
			Metropolitan metropolitans = metropolitanRepository.findByMetropolitan(metropolitan);
			if(metropolitans==null) {
				throw new NotFoundException("Metropolitan with name "+ metropolitan +" not found. Try with different name.");
			}
			vdcs.setMetropolitan(metropolitans);

		}

		vdcRepository.save(vdcs);

	}

}
