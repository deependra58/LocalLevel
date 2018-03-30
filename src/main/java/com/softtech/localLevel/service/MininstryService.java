package com.softtech.localLevel.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softech.localLevel.request.MinistryCreationDto;
import com.softech.localLevel.request.MinistryEditRequest;
import com.softtech.localLevel.dto.MinistryDetailsDto;
import com.softtech.localLevel.model.Ministry;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.StateRepository;
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

	@Autowired
	private StateRepository stateRepository;

	@Transactional
	public Ministry createCentralMinistry(MinistryCreationDto minsitryCreationDto) {

		Ministry minist = ministryRepository.findByMinistryName(minsitryCreationDto.getMinistryName());
		if (minist != null) {
			throw new AlreadyExistException(
					"Ministry with name " + minsitryCreationDto.getMinistryName() + " already exist");
		}
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
		Ministry ministry = ministryRepository.findByMinistryNameAndGovTypeAndStatusNot(ministryName, GovType.CENTRAL,
				Status.DELETED);
		if (ministry == null) {
			throw new NotFoundException("Ministry with " + ministryName + " not found.");
		}
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
	public List<MinistryResponseDto> getLocalMinistries(String stateName) {

		List<MinistryResponseDto> ministryResponseList = new ArrayList<MinistryResponseDto>();
		State state = stateRepository.findByState(stateName);
		List<Ministry> ministries = ministryRepository.findByGovTypeAndStatusNotAndState(GovType.LOCAL, Status.DELETED,
				new State(state.getId()));
		ministries.stream().forEach(u -> {
			MinistryResponseDto ministryResponseDto = new MinistryResponseDto();
			ministryResponseDto.setMinistryName(u.getMinistryName());

			ministryResponseList.add(ministryResponseDto);

		});
		return ministryResponseList;

	}

	@Transactional
	public Ministry createLocalMinistry(MinistryCreationDto minsitryCreationDto, String stateName) {
		State state = stateRepository.findByState(stateName);
		System.out.println("Ministry Name:" + minsitryCreationDto.getMinistryName() + "State Id:" + state.getId());
		Ministry minist = ministryRepository.findByMinistryNameAndState(minsitryCreationDto.getMinistryName(),
				new State(state.getId()));
		System.out.println("Ministry Info" + minist.toString());

		if (minist != null) {
			throw new AlreadyExistException(
					"Ministry with name " + minsitryCreationDto.getMinistryName() + " already exist");
		}
		System.out.println("hello world");
		Ministry ministry = new Ministry();
		ministry.setMinisterName(minsitryCreationDto.getMinisterName());
		ministry.setMinisterEmail(minsitryCreationDto.getMinisterEmail());
		ministry.setContactNumber(minsitryCreationDto.getContactNumber());
		ministry.setMinistryName(minsitryCreationDto.getMinistryName());
		ministry.setState(state);
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
	public void deleteLocalMinistry(String ministryName, String stateName) {
		State state = stateRepository.findByState(stateName);
		Ministry ministry = ministryRepository.findByMinistryNameAndGovTypeAndStateAndStatusNot(ministryName,
				GovType.LOCAL, new State(state.getId()), Status.DELETED);
		if (ministry == null) {
			throw new NotFoundException("Ministry with " + ministryName + " not found.");
		}
		ministry.setStatus(Status.DELETED);
		ministryRepository.save(ministry);

	}

	@Transactional
	public Ministry editCentralMinistry(MinistryEditRequest ministryEditRequest, String ministryName) {
		Ministry ministry = ministryRepository.findByMinistryNameAndGovTypeAndStatusNot(ministryName, GovType.CENTRAL,
				Status.DELETED);
		if (ministry == null) {
			throw new NotFoundException("Ministry with " + ministryName + " not found.");
		}
		Ministry savedMinistry = new Ministry();

		ministry.setMinistryName(ministryEditRequest.getMinistryName());
		ministry.setMinisterName(ministryEditRequest.getMinisterName());
		ministry.setMinisterEmail(ministryEditRequest.getMinisterEmail());
		ministry.setContactNumber(ministryEditRequest.getContactNumber());
		savedMinistry = ministryRepository.save(ministry);
		return savedMinistry;
	}

	@Transactional
	public Ministry editLocalMinistry(MinistryEditRequest ministryEditRequest, String ministryName, String state) {
		State states = stateRepository.findByState(state);
		Ministry ministry = ministryRepository.findByMinistryNameAndStateAndGovTypeAndStatusNot(ministryName,
				new State(states.getId()), GovType.LOCAL, Status.DELETED);
		if (ministry == null) {
			throw new NotFoundException("Ministry with " + ministryName + " not found.");
		}
		Ministry savedMinistry = new Ministry();

		ministry.setMinistryName(ministryEditRequest.getMinistryName());
		ministry.setMinisterName(ministryEditRequest.getMinisterName());
		ministry.setMinisterEmail(ministryEditRequest.getMinisterEmail());
		ministry.setContactNumber(ministryEditRequest.getContactNumber());
		savedMinistry = ministryRepository.save(ministry);
		return savedMinistry;
	}

	@Transactional
	public MinistryDetailsDto showCentralDetails(String ministryName) {
		Ministry ministry = ministryRepository.findByMinistryNameAndStatusNot(ministryName, Status.DELETED);
		if (ministry == null) {
			throw new NotFoundException("Ministry with given name " + ministryName + " not found!");
		}
		MinistryDetailsDto mdto = new MinistryDetailsDto();
		mdto.setMinistryName(ministry.getMinistryName());
		mdto.setMinisterName(ministry.getMinisterName());
		mdto.setMinisterImage(ministry.getMinisterImage());
		mdto.setContactNumber(ministry.getContactNumber());
		mdto.setMinisterEmail(ministry.getMinisterEmail());
		System.out.println(mdto.toString());
		return mdto;
	}

	@Transactional
	public MinistryDetailsDto showLocalDetails(String ministryName, String stateName) {
		State state = stateRepository.findByState(stateName);
		Ministry ministry = ministryRepository.findByMinistryNameAndStatusNotAndState(ministryName, Status.DELETED,
				new State(state.getId()));
		if (ministry == null) {
			throw new NotFoundException("Ministry with given name " + ministryName + " not found!");
		}
		MinistryDetailsDto mdto = new MinistryDetailsDto();
		mdto.setMinistryName(ministry.getMinistryName());
		mdto.setMinisterName(ministry.getMinisterName());
		mdto.setMinisterImage(ministry.getMinisterImage());
		mdto.setContactNumber(ministry.getContactNumber());
		mdto.setMinisterEmail(ministry.getMinisterEmail());
		System.out.println(mdto.toString());
		return mdto;
	}

}
