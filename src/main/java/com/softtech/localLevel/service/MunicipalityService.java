package com.softtech.localLevel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.localLevel.dto.MunicipalityDetailsDto;
import com.softtech.localLevel.model.Municipality;
import com.softtech.localLevel.repository.MunicipalityRepository;
import com.softtech.localLevel.util.Status;

@Service
public class MunicipalityService {
	
	@Autowired
	private MunicipalityRepository municipalityRespository;

	public MunicipalityDetailsDto showMunicipalityDetails(String municipality) {
		Municipality municipal=municipalityRespository.findByMunicipalAndStatusNot(municipality,Status.ACTIVE);
		MunicipalityDetailsDto municipalityDetailsDto=new MunicipalityDetailsDto();
		municipalityDetailsDto.setMunicipal(municipal.getMunicipal());
		municipalityDetailsDto.setPopulation(municipal.getPopulation());
		municipalityDetailsDto.setArea(municipal.getArea());
		municipalityDetailsDto.setDensity(municipal.getDensity());
		municipalityDetailsDto.setWebsite(municipal.getWebsite());
		municipalityDetailsDto.setMayor(municipal.getMayor());
		municipalityDetailsDto.setMayorContact(municipal.getMayorContact());
		municipalityDetailsDto.setMayorEmail(municipal.getMayorEmail());
		municipalityDetailsDto.setDeputMayor(municipal.getDeputMayor());
		municipalityDetailsDto.setDeputMayorContact(municipal.getDeputMayorContact());
		municipalityDetailsDto.setDeputMayorEmail(municipal.getDeputMayorEmail());
		
		municipalityDetailsDto.setStatus(Status.ACTIVE);
		
		return municipalityDetailsDto;
	}

}
