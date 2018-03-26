package com.softtech.localLevel.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.NotFoundException;
import com.softech.localLevel.request.MinistryCreationDto;
import com.softech.localLevel.request.MinistryEditRequest;
import com.softtech.localLevel.model.Ministry;
import com.softtech.localLevel.repository.ministryRepository;
import com.softtech.localLevel.response.MinistryResponseDto;
import com.softtech.localLevel.util.FileUtil;
import com.softtech.localLevel.util.GovType;
import com.softtech.localLevel.util.Status;

@Service
public class MininstryService {

	@SuppressWarnings("unused")
	@Autowired
	private ministryRepository ministryRepository;

	@Transactional
	public Ministry createCentralMinistry(MinistryCreationDto minsitryCreationDto) {
		Ministry ministry = new Ministry();
		ministry.setMinisterName(minsitryCreationDto.getMinisterName());
		ministry.setMinisterEmail(minsitryCreationDto.getMinisterEmail());
		ministry.setContactNumber(minsitryCreationDto.getContactNumber());
		ministry.setMinistryName(minsitryCreationDto.getMinistryName());
		ministry.setGovType(GovType.CENTRAL);
		ministry.setStatus(Status.ACTIVE);
		File file = null;
		try {

			if (minsitryCreationDto.getMinisterImage() != null) {
				file = FileUtil.write(String.valueOf(new Date().getTime()).concat(".").concat("png"),
						minsitryCreationDto.getMinisterImage());

				if (file != null)
					ministry.setMinisterImage(file.getAbsolutePath());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (file != null) {
				file.delete();
			}
		}
		Ministry savedMinistry = new Ministry();
		savedMinistry = ministryRepository.save(ministry);
		return savedMinistry;
	}

	@Transactional
	public void deleteMinistry(String ministryName) {
		Ministry ministry = ministryRepository.findByMinistryNameAndGovType(ministryName, GovType.CENTRAL);
		ministry.setStatus(Status.DELETED);
		ministryRepository.save(ministry);

	}

	@Transactional
	public List<MinistryResponseDto> getCentralMinistries() {

		List<MinistryResponseDto> ministryResponseList = new ArrayList<MinistryResponseDto>();
		List<Ministry> ministries = ministryRepository.findByGovTypeAndStatusNot(GovType.CENTRAL, Status.DELETED);
		ministries.stream().forEach(u -> {
			MinistryResponseDto ministryResponseDto = new MinistryResponseDto();
			ministryResponseDto.setMinistryName(u.getMinistryName());

			ministryResponseList.add(ministryResponseDto);

		});
		return ministryResponseList;

	}

	@Transactional
	public List<MinistryResponseDto> getLocalMinistries() {

		List<MinistryResponseDto> ministryResponseList = new ArrayList<MinistryResponseDto>();
		List<Ministry> ministries = ministryRepository.findByGovTypeAndStatusNot(GovType.LOCAL, Status.DELETED);
		ministries.stream().forEach(u -> {
			MinistryResponseDto ministryResponseDto = new MinistryResponseDto();
			ministryResponseDto.setMinistryName(u.getMinistryName());

			ministryResponseList.add(ministryResponseDto);

		});
		return ministryResponseList;

	}

	@Transactional
	public Ministry createLocalMinistry(MinistryCreationDto minsitryCreationDto) {
		Ministry ministry = new Ministry();
		ministry.setMinisterName(minsitryCreationDto.getMinisterName());
		ministry.setMinisterEmail(minsitryCreationDto.getMinisterEmail());
		ministry.setContactNumber(minsitryCreationDto.getContactNumber());
		ministry.setMinistryName(minsitryCreationDto.getMinistryName());
		ministry.setGovType(GovType.LOCAL);
		ministry.setStatus(Status.ACTIVE);
		File file = null;
		try {

			if (minsitryCreationDto.getMinisterImage() != null) {
				file = FileUtil.write(String.valueOf(new Date().getTime()).concat(".").concat("png"),
						minsitryCreationDto.getMinisterImage());

				if (file != null)
					ministry.setMinisterImage(file.getAbsolutePath());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (file != null) {
				file.delete();
			}
		}
		Ministry savedMinistry = new Ministry();
		savedMinistry = ministryRepository.save(ministry);
		return savedMinistry;
	}

	@Transactional
	public void deleteLocalMinistry(String ministryName) {
		Ministry ministry = ministryRepository.findByMinistryNameAndGovType(ministryName, GovType.LOCAL);
		ministry.setStatus(Status.DELETED);
		ministryRepository.save(ministry);

	}

	@Transactional
	public Ministry editCentralMinistry(MinistryEditRequest ministryEditRequest, String ministryName) {
		Ministry ministry=ministryRepository.findByMinistryNameAndGovTypeAndStatusNot(ministryName,GovType.CENTRAL, Status.DELETED);
		Ministry savedMinistry=new Ministry();
		if(ministry!=null) {
			
			ministry.setMinistryName(ministryEditRequest.getMinistryName());
			ministry.setMinisterName(ministryEditRequest.getMinisterName());
			ministry.setMinisterEmail(ministryEditRequest.getMinisterEmail());
			ministry.setContactNumber(ministryEditRequest.getContactNumber());
			savedMinistry=ministryRepository.save(ministry);
		}
		else {
			 throw new NotFoundException("Ministry with "+ministryName+" not found.");
		}
		return savedMinistry;
	}

	public Ministry editLocalMinistry(MinistryEditRequest ministryEditRequest, String ministryName) {
		Ministry ministry=ministryRepository.findByMinistryNameAndGovTypeAndStatusNot(ministryName,GovType.LOCAL, Status.DELETED);
		Ministry savedMinistry=new Ministry();
		if(ministry!=null) {
			
			ministry.setMinistryName(ministryEditRequest.getMinistryName());
			ministry.setMinisterName(ministryEditRequest.getMinisterName());
			ministry.setMinisterEmail(ministryEditRequest.getMinisterEmail());
			ministry.setContactNumber(ministryEditRequest.getContactNumber());
			savedMinistry=ministryRepository.save(ministry);
		}
		else {
			 throw new NotFoundException("Ministry with "+ministryName+" not found.");
		}
		return savedMinistry;
	}

}
