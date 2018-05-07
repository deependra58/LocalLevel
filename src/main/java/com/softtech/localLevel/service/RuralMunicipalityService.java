package com.softtech.localLevel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.localLevel.dto.RuralMunicipalDetailsDto;
import com.softtech.localLevel.model.RuralMunicipality;
import com.softtech.localLevel.repository.RuralMunicipalityRepository;
import com.softtech.localLevel.util.Status;

@Service
public class RuralMunicipalityService {
	
	@Autowired
	private RuralMunicipalityRepository ruralMunicipalityRepository;

	public RuralMunicipalDetailsDto showDetails(String ruralMunicipal) {
		RuralMunicipality ruralMunicipality=ruralMunicipalityRepository.findByRuralMunicipalAndStatusNot(ruralMunicipal,Status.ACTIVE);
		System.out.println(ruralMunicipality.toString());
		RuralMunicipalDetailsDto ruralMunicipalDetailsDto=new RuralMunicipalDetailsDto();
		ruralMunicipalDetailsDto.setRuralMunicipal(ruralMunicipality.getRuralMunicipal());
		ruralMunicipalDetailsDto.setPopulation(ruralMunicipality.getPopulation());
		ruralMunicipalDetailsDto.setPopulation(ruralMunicipality.getPopulation());
		ruralMunicipalDetailsDto.setArea(ruralMunicipality.getArea());
		ruralMunicipalDetailsDto.setDensity(ruralMunicipality.getDensity());
		ruralMunicipalDetailsDto.setWebsite(ruralMunicipality.getWebsite());
		ruralMunicipalDetailsDto.setChairmen(ruralMunicipality.getChairmen());
		ruralMunicipalDetailsDto.setChairmenContact(ruralMunicipality.getChairmenContact());
		ruralMunicipalDetailsDto.setChairmenEmail(ruralMunicipality.getChairmenEmail());
		ruralMunicipalDetailsDto.setViceChairmen(ruralMunicipality.getViceChairmen());
		ruralMunicipalDetailsDto.setViceChairmenContact(ruralMunicipality.getViceChairmenContact());
		ruralMunicipalDetailsDto.setViceChairmenEmail(ruralMunicipality.getViceChairmenEmail());
		ruralMunicipalDetailsDto.setStatus(Status.ACTIVE);
		return ruralMunicipalDetailsDto;
		
		
	}

}
