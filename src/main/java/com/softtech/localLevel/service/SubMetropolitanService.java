package com.softtech.localLevel.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.localLevel.dto.SubMetropolitanDetailsDto;
import com.softtech.localLevel.model.SubMetropolitan;
import com.softtech.localLevel.repository.SubMetropolitanRepository;
import com.softtech.localLevel.util.Status;
@Service
public class SubMetropolitanService {
	
	@Autowired
	private SubMetropolitanRepository subMetropolitanRepository;

	public SubMetropolitanDetailsDto getSubMetropolitanDetails(String SubMetropolitan) {
		SubMetropolitan subMetropolitans=subMetropolitanRepository.findBySubMetropolitanAndStatusNot(SubMetropolitan,Status.DELETED);
		SubMetropolitanDetailsDto subMetropolitanDetailsDto=new SubMetropolitanDetailsDto();
		subMetropolitanDetailsDto.setSubMetropolitan(subMetropolitans.getSubMetropolitan());
		subMetropolitanDetailsDto.setArea(subMetropolitans.getArea());
		subMetropolitanDetailsDto.setPopulation(subMetropolitans.getPopulation());
		subMetropolitanDetailsDto.setDensity(subMetropolitans.getDensity());
		subMetropolitanDetailsDto.setWebsite(subMetropolitans.getWebsite());
		subMetropolitanDetailsDto.setMayor(subMetropolitans.getMayor());
		subMetropolitanDetailsDto.setMayorContact(subMetropolitans.getMayorContact());
		subMetropolitanDetailsDto.setDeputMayor(subMetropolitans.getDeputMayor());
		subMetropolitanDetailsDto.setMayorEmail(subMetropolitans.getMayorEmail());
		subMetropolitanDetailsDto.setDeputMayor(subMetropolitans.getDeputMayor());
		subMetropolitanDetailsDto.setDeputMayorContact(subMetropolitans.getDeputMayorContact());
		subMetropolitanDetailsDto.setDeputMayorEmail(subMetropolitans.getDeputMayorEmail());
		subMetropolitanDetailsDto.setStatus(Status.ACTIVE);
		
		return subMetropolitanDetailsDto;
	}

}
