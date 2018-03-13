package com.softtech.localLevel.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.localLevel.model.NewVdc;
import com.softtech.localLevel.model.OldVdc;
import com.softtech.localLevel.model.Ward;
import com.softtech.localLevel.repository.NewVdcRepository;
import com.softtech.localLevel.repository.OldVdcRepository;
import com.softtech.localLevel.repository.WardRepository;
import com.softtech.localLevel.response.WardResponseDto;
import org.slf4j.Logger;




@Service
public class WardService {
	
	private static final Logger LOG=LoggerFactory.getLogger(WardService.class);
	
	@Autowired
	private OldVdcRepository oldVdcRepository;
	@Autowired
	private WardRepository wardRepository;
	@Autowired
	private NewVdcRepository newVdcRepository;

	
	public WardResponseDto getWard(Long oldWardId, String oldVdcName) {
		
		LOG.info("Request accepted to show newWard and new vdc name");
		OldVdc oldVdcs=oldVdcRepository.findByOldVdc(oldVdcName);
		Long id=oldVdcs.getId();
		Ward wards=wardRepository.findByOldWardIdAndOldVdc(oldWardId, new OldVdc(id));
		WardResponseDto wardResponseDto=new WardResponseDto();
		wardResponseDto.setNewWardId(wards.getNewWardId());
		NewVdc newVdcs=newVdcRepository.findById(oldVdcs.getNewVdc().getId());
		wardResponseDto.setNewVdc(newVdcs.getNewVdc());
		return wardResponseDto;
		
	}

	
}
