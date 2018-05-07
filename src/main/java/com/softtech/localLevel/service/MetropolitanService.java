package com.softtech.localLevel.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.localLevel.dto.MetropolitanDetailsDto;
import com.softtech.localLevel.model.Metropolitan;
import com.softtech.localLevel.repository.MetropolitanRepository;
import com.softtech.localLevel.util.Status;
@Service
public class MetropolitanService {

	@Autowired
	private MetropolitanRepository metropolitanRepository;
	public MetropolitanDetailsDto getMetropolitanDetails(String metropolitan) {
		Metropolitan metropolitans=metropolitanRepository.findByMetropolitanAndStatusNot(metropolitan, Status.DELETED);
		MetropolitanDetailsDto metropolitanDetailsDto=new MetropolitanDetailsDto();
		metropolitanDetailsDto.setMetropolitan(metropolitans.getMetropolitan());
		metropolitanDetailsDto.setArea(metropolitans.getArea());
		metropolitanDetailsDto.setPopulation(metropolitans.getPopulation());
		metropolitanDetailsDto.setDensity(metropolitans.getDensity());
		metropolitanDetailsDto.setWebsite(metropolitans.getWebsite());
		metropolitanDetailsDto.setMayor(metropolitans.getMayor());
		metropolitanDetailsDto.setMayorContact(metropolitans.getMayorContact());
		metropolitanDetailsDto.setDeputMayor(metropolitans.getDeputMayor());
		metropolitanDetailsDto.setMayorEmail(metropolitans.getMayorEmail());
		metropolitanDetailsDto.setDeputMayor(metropolitans.getDeputMayor());
		metropolitanDetailsDto.setDeputMayorContact(metropolitans.getDeputMayorContact());
		metropolitanDetailsDto.setDeputMayorEmail(metropolitans.getDeputMayorEmail());
		metropolitanDetailsDto.setStatus(Status.ACTIVE);
		return metropolitanDetailsDto;
	}

}
