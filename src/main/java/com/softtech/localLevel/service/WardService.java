package com.softtech.localLevel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.localLevel.model.NewVdc;
import com.softtech.localLevel.model.OldVdc;
import com.softtech.localLevel.model.Ward;
import com.softtech.localLevel.repository.NewVdcRepository;
import com.softtech.localLevel.repository.OldVdcRepository;
import com.softtech.localLevel.repository.WardRepository;
import com.softtech.localLevel.response.WardResponseDto;



@Service
public class WardService {
	@Autowired
	private OldVdcRepository oldVdcRepository;
	@Autowired
	private WardRepository wardRepository;
	@Autowired
	private NewVdcRepository newVdcRepository;

	public WardResponseDto getWard(Long oldWardId, String oldVdcName) {
		
		OldVdc oldVdcs=oldVdcRepository.findByOldVdc(oldVdcName);
		//System.out.println("Old Vdc name "+ oldVdcs.getOldVdc());
		Long id=oldVdcs.getId();
		//System.out.println("Old Vdc id "+ oldVdcs.getId());
		
		Ward wards=wardRepository.findByOldWardIdAndOldVdc(oldWardId, new OldVdc(id));
		WardResponseDto wardResponseDto=new WardResponseDto();
		wardResponseDto.setNewWardId(wards.getNewWardId());
		//System.out.println("Ward ID "+ wards.getNewWardId());
		/* for finding new vdc again*/
		NewVdc newVdcs=newVdcRepository.findById(oldVdcs.getNewVdc().getId());
		//System.out.println("New Vdc name "+ newVdcs.getNewVdc());
		
		wardResponseDto.setNewVdc(newVdcs.getNewVdc());
		return wardResponseDto;
		
	}

	
}
