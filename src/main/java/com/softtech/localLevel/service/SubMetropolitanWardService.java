package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.NotFoundException;
import com.softech.localLevel.request.SubMetropolitanWardCreationRequest;
import com.softech.localLevel.request.SubMetropolitanWardEditRequest;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.SubMetropolitan;
import com.softtech.localLevel.model.SubMetropolitanWard;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.SubMetropolitanRepository;
import com.softtech.localLevel.repository.SubMetropolitanWardRepository;
import com.softtech.localLevel.response.SubMetropolitanWardResponse;
import com.softtech.localLevel.util.Status;

/*
 * <<This is the service for SubMetropolitan >>
 * @Author Deependra
 * @Since 10, May 2018
 * @Version 1.0.1
 * 
 * */
@Service
public class SubMetropolitanWardService {
	@Autowired
	private SubMetropolitanWardRepository subMetropolitanWardRepository;

	@Autowired
	private SubMetropolitanRepository subMetropolitanRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Transactional
	public void postSubMetropolitanWard(SubMetropolitanWardCreationRequest subMetropolitanWardCreationRequest) {
		SubMetropolitanWard rmw = new SubMetropolitanWard();

		SubMetropolitan subMetropolitan = subMetropolitanRepository.findBySubMetropolitanAndStatusNot(
				subMetropolitanWardCreationRequest.getSubMetropolitan(), Status.DELETED);

		if (subMetropolitan == null) {
			throw new NotFoundException("Rural Municipality with name not found!");
		}

		rmw.setDistrict_id(subMetropolitan.getDistrict().getId());
		rmw.setSubMetropolitan(subMetropolitan);
		rmw.setNewWardId(subMetropolitanWardCreationRequest.getNewWardId());
		rmw.setNewWardName(subMetropolitanWardCreationRequest.getNewWardName());
		rmw.setOldWardId(subMetropolitanWardCreationRequest.getOldWardId());
		rmw.setOldVdcName(subMetropolitanWardCreationRequest.getOldVdcName());
		rmw.setOldWardName(subMetropolitanWardCreationRequest.getOldWardName());
		rmw.setStatus(Status.ACTIVE);
		subMetropolitanWardRepository.save(rmw);

	}

	@Transactional
	public List<SubMetropolitanWardResponse> getOldVdc(String subMetropolitan, Long newWardId) {
		List<SubMetropolitanWardResponse> rmwr = new ArrayList<SubMetropolitanWardResponse>();
		SubMetropolitan subMetropolitans = subMetropolitanRepository.findBySubMetropolitanAndStatusNot(subMetropolitan,
				Status.DELETED);
		if (subMetropolitans == null) {
			throw new NotFoundException("Rural Municipality with name " + subMetropolitan + " not found.");
		}
		List<SubMetropolitanWard> rm = subMetropolitanWardRepository
				.findAllBySubMetropolitanAndNewWardIdAndStatusNot(subMetropolitans, newWardId, Status.DELETED);
		rm.stream().forEach(u -> {
			SubMetropolitanWardResponse rmr = new SubMetropolitanWardResponse();
			District district = districtRepository.findById(u.getDistrict_id());
			rmr.setDistrict(district.getDistrict());
			rmr.setId(u.getId());
			rmr.setOldWardId(u.getOldWardId());
			rmr.setOldVdcName(u.getOldVdcName());
			rmr.setOldWardName(u.getOldWardName());

			rmwr.add(rmr);

		});
		return rmwr;
	}

	@Transactional
	public SubMetropolitanWardResponse getOldVdcInfo(String oldVdcName, Long oldWardId) {
		SubMetropolitanWardResponse rmwr = new SubMetropolitanWardResponse();
		SubMetropolitanWard rmw = subMetropolitanWardRepository.findByOldVdcNameAndOldWardIdAndStatusNot(oldVdcName,
				oldWardId, Status.DELETED);
		if (rmw == null) {
			throw new NotFoundException("Not found!!");
		}
		SubMetropolitan sm = subMetropolitanRepository.findByIdAndStatusNot(rmw.getSubMetropolitan().getId(),
				Status.DELETED);
		rmwr.setSubMetropolitan(sm.getSubMetropolitan());
		rmwr.setNewWardId(rmw.getNewWardId());
		rmwr.setNewWardName(rmw.getNewWardName());
		return rmwr;
	}

	@Transactional
	public void editSubMetropolitan(Long id, SubMetropolitanWardEditRequest subMetropolitanWardEditRequest) {
		SubMetropolitanWard rmw = subMetropolitanWardRepository.findByIdAndStatusNot(id, Status.DELETED);
		if (rmw == null) {
			throw new NotFoundException("SubMetropolitanWard with id:" + id + " not found!");
		}

		SubMetropolitan sm = subMetropolitanRepository
				.findBySubMetropolitanAndStatusNot(subMetropolitanWardEditRequest.getSubMetropolitan(), Status.DELETED);
		if (sm == null) {
			throw new NotFoundException(
					"SubMetropolitan with name " + subMetropolitanWardEditRequest.getSubMetropolitan() + " not found.");
		}
		rmw.setDistrict_id(sm.getDistrict().getId());
		rmw.setSubMetropolitan(sm);
		rmw.setNewWardId(subMetropolitanWardEditRequest.getNewWardId());
		rmw.setNewWardName(subMetropolitanWardEditRequest.getNewWardName());
		rmw.setOldWardId(subMetropolitanWardEditRequest.getOldWardId());
		rmw.setOldVdcName(subMetropolitanWardEditRequest.getOldVdcName());
		rmw.setOldWardName(subMetropolitanWardEditRequest.getOldWardName());
		rmw.setStatus(Status.ACTIVE);
		subMetropolitanWardRepository.save(rmw);
	}

	@Transactional
	public void deleteInfo(Long id) {
		SubMetropolitanWard rmw = subMetropolitanWardRepository.findByIdAndStatusNot(id, Status.DELETED);
		if (rmw == null) {
			throw new NotFoundException("SubMetropolitanWard with id:" + id + " not found!");
		}
		rmw.setStatus(Status.DELETED);
		subMetropolitanWardRepository.save(rmw);

	}

}