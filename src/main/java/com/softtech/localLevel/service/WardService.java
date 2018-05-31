package com.softtech.localLevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.localLevel.repository.WardRepository;
import com.softtech.localLevel.response.WardResponseDto;

@Service
public class WardService {
	
	private static final Logger LOG=LoggerFactory.getLogger(WardService.class);
	
	@Autowired
	private WardRepository wardRepository;

	public WardResponseDto getWard(Long oldWardId, String oldVdcName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
