package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.NaturalResources;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.NaturalResourcesRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.LakesResponseDto;
import com.softtech.localLevel.response.MountainsResponseDto;
import com.softtech.localLevel.response.ProtectedAreaResponseDto;
import com.softtech.localLevel.response.RiversResponseDto;
import com.softtech.localLevel.response.WaterfallResponseDto;
import com.softtech.localLevel.util.Status;

@Service
public class NaturalResourcesService {
	@Autowired
	private NaturalResourcesRepository naturalResourcesRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Transactional
	public NaturalResources postMountain(String mountains, String height, String mountainDistrict) {
		NaturalResources naturalResources = new NaturalResources();
		District district = districtRepository.findByDistrictAndStatusNot(mountainDistrict, Status.DELETED);
		if (district == null) {
			throw new NotFoundException("District with name " + mountainDistrict + " not found.");
		}
		NaturalResources naturalRes = naturalResourcesRepository.findByMountain(mountains);
		if (naturalRes != null) {
			throw new AlreadyExistException("Mountain with name " + mountains + " already exist.");
		}
		naturalResources.setDistrict(district);
		naturalResources.setMountain(mountains);
		State state = stateRepository.findById(district.getState().getId());
		naturalResources.setState(state);
		naturalResources.setMountainHeight(height);
		naturalResourcesRepository.save(naturalResources);
		return naturalResources;
	}

	@Transactional
	public NaturalResources postRiver(String river_name, String riverDistrict) {
		NaturalResources naturalResources = new NaturalResources();
		District district = districtRepository.findByDistrictAndStatusNot(riverDistrict, Status.DELETED);
		if (district == null) {
			throw new NotFoundException("District with name " + riverDistrict + " not found.");
		}
		NaturalResources naturalRes = naturalResourcesRepository.findByRiver(river_name);
		if (naturalRes != null) {
			throw new AlreadyExistException("River with name " + river_name + " already exist.");
		}
		naturalResources.setDistrict(district);
		naturalResources.setRiver(river_name);
		State state = stateRepository.findById(district.getState().getId());
		naturalResources.setState(state);
		naturalResourcesRepository.save(naturalResources);
		return naturalResources;

	}

	@Transactional
	public NaturalResources postLake(String lake_name, String lakeDistrict) {
		NaturalResources naturalResources = new NaturalResources();
		District district = districtRepository.findByDistrictAndStatusNot(lakeDistrict, Status.DELETED);
		if (district == null) {
			throw new NotFoundException("District with name " + lakeDistrict + " not found.");
		}
		NaturalResources naturalRes = naturalResourcesRepository.findBylake(lake_name);
		if (naturalRes != null) {
			throw new AlreadyExistException("lake with name " + lake_name + " already exist.");
		}
		naturalResources.setDistrict(district);
		naturalResources.setLake(lake_name);
		State state = stateRepository.findById(district.getState().getId());
		naturalResources.setState(state);
		naturalResourcesRepository.save(naturalResources);
		return naturalResources;
	}

	@Transactional
	public NaturalResources postWaterfall(String waterfall_name, String districts) {
		NaturalResources naturalResources = new NaturalResources();
		District district = districtRepository.findByDistrictAndStatusNot(districts, Status.DELETED);
		if (district == null) {
			throw new NotFoundException("District with name " + districts + " not found.");
		}
		NaturalResources naturalRes = naturalResourcesRepository.findByWaterfalls(waterfall_name);
		if (naturalRes != null) {
			throw new AlreadyExistException("Waterfall with name " + waterfall_name + " already exist.");
		}
		naturalResources.setDistrict(district);
		naturalResources.setWaterfalls(waterfall_name);
		State state = stateRepository.findById(district.getState().getId());
		naturalResources.setState(state);
		naturalResourcesRepository.save(naturalResources);
		return naturalResources;
	}

	@Transactional
	public NaturalResources postProtectedArea(String protectedArea_name, String districts) {
		NaturalResources naturalResources = new NaturalResources();
		District district = districtRepository.findByDistrictAndStatusNot(districts, Status.DELETED);
		if (district == null) {
			throw new NotFoundException("District with name " + districts + " not found.");
		}
		NaturalResources naturalRes = naturalResourcesRepository.findByProtectedAreas(protectedArea_name);
		if (naturalRes != null) {
			throw new AlreadyExistException("Protected Area with name " + protectedArea_name + " already exist.");
		}
		naturalResources.setDistrict(district);
		naturalResources.setProtectedAreas(protectedArea_name);
		State state = stateRepository.findById(district.getState().getId());
		naturalResources.setState(state);
		naturalResourcesRepository.save(naturalResources);
		return naturalResources;

	}

	@Transactional
	public List<MountainsResponseDto> getMountain(String state) {
		
		List<MountainsResponseDto> mountainResponseDtoList = new ArrayList<MountainsResponseDto>();
		State states = stateRepository.findByState(state);
		List<NaturalResources> naturalResources = naturalResourcesRepository.findAllByState(new State(states.getId()));
		naturalResources.stream().forEach(u -> {
			if (u.getMountain() != null) {
				MountainsResponseDto mountainsResponseDto = new MountainsResponseDto();
				mountainsResponseDto.setMountain(u.getMountain());
				mountainsResponseDto.setMountainHeight(u.getMountainHeight());
				District district=districtRepository.findById(u.getDistrict().getId());
				System.out.println(district.toString());
				mountainsResponseDto.setDistrict(district.getDistrict());
				mountainResponseDtoList.add(mountainsResponseDto);
			}
		});
		return mountainResponseDtoList;

	}

	@Transactional
	public List<RiversResponseDto> getRivers(String state) {
		State states = stateRepository.findByState(state);
		List<NaturalResources> naturalResources = naturalResourcesRepository.findAllByState(new State(states.getId()));
		List<RiversResponseDto> riversResponseDtoList = new ArrayList<RiversResponseDto>();
		naturalResources.stream().forEach(u -> {
			if (u.getRiver() != null) {
				RiversResponseDto riversResponseDto = new RiversResponseDto();
				District district=districtRepository.findById(u.getDistrict().getId());
				riversResponseDto.setDistrict(district.getDistrict());
				riversResponseDto.setRiver(u.getRiver());
				riversResponseDtoList.add(riversResponseDto);
			}
		});
		return riversResponseDtoList;

	}

	@Transactional
	public List<LakesResponseDto> getLakes(String state) {
		State states = stateRepository.findByState(state);
		List<NaturalResources> naturalResources = naturalResourcesRepository.findAllByState(new State(states.getId()));
		List<LakesResponseDto> lakesResponseDtoList = new ArrayList<LakesResponseDto>();
		naturalResources.stream().forEach(u -> {
			if (u.getLake() != null) {
				LakesResponseDto lakesResponseDto = new LakesResponseDto();
				District district=districtRepository.findById(u.getDistrict().getId());
				lakesResponseDto.setDistrict(district.getDistrict());
				lakesResponseDto.setLake(u.getLake());
				lakesResponseDtoList.add(lakesResponseDto);
			}
		});
		return lakesResponseDtoList;
	}

	@Transactional
	public List<WaterfallResponseDto> getWaterfall(String state) {
		State states = stateRepository.findByState(state);
		List<NaturalResources> naturalResources = naturalResourcesRepository.findAllByState(new State(states.getId()));
		List<WaterfallResponseDto> waterfallResponseDtoList = new ArrayList<WaterfallResponseDto>();
		naturalResources.stream().forEach(u -> {
			if (u.getWaterfalls() != null) {
				WaterfallResponseDto waterfallResponseDto = new WaterfallResponseDto();
				District district=districtRepository.findById(u.getDistrict().getId());
				waterfallResponseDto.setDistrict(district.getDistrict());
				waterfallResponseDto.setWaterfall(u.getWaterfalls());
				waterfallResponseDtoList.add(waterfallResponseDto);
			}
		});
		return waterfallResponseDtoList;
	}

	@Transactional
	public List<ProtectedAreaResponseDto> getProtectedArea(String state) {
		State states = stateRepository.findByState(state);
		List<NaturalResources> naturalResources = naturalResourcesRepository.findAllByState(new State(states.getId()));
		List<ProtectedAreaResponseDto> protectedAreaResponseDtoList = new ArrayList<ProtectedAreaResponseDto>();
		naturalResources.stream().forEach(u -> {
			if (u.getProtectedAreas() != null) {
				ProtectedAreaResponseDto protectedAreaResponseDto = new ProtectedAreaResponseDto();
				District district=districtRepository.findById(u.getDistrict().getId());
				protectedAreaResponseDto.setDistrict(district.getDistrict());
				protectedAreaResponseDto.setProtectedAreas(u.getProtectedAreas());
				protectedAreaResponseDtoList.add(protectedAreaResponseDto);
			}
		});
		return protectedAreaResponseDtoList;
	}

}
