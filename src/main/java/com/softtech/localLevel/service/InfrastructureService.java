package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Infrastructure;
import com.softtech.localLevel.model.NaturalResources;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.InfrastructureRepository;
import com.softtech.localLevel.repository.NaturalResourcesRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.AirportsResponseDto;
import com.softtech.localLevel.response.HospitalResponseDto;
import com.softtech.localLevel.response.HydropowerResponseDto;
import com.softtech.localLevel.response.IndustriesResponseDto;

@Service
public class InfrastructureService {

	@Autowired
	private InfrastructureRepository infrastructureRepository;

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private StateRepository stateRepository;

	@Transactional
	public Infrastructure postAirports(String airportName, String district) {
		Infrastructure infrac = new Infrastructure();
		District districts = districtRepository.findByDistrict(district);
		if (districts == null) {
			throw new NotFoundException("District with name " + district + " not found.");
		}
		Infrastructure infracs = infrastructureRepository.findByAirports(airportName);
		if (infracs != null) {
			throw new AlreadyExistException("Airport with name " + airportName + " already exist.");
		}
		infrac.setDistrict(districts);
		infrac.setAirports(airportName);
		State state = stateRepository.findById(districts.getState().getId());
		infrac.setState(state);
		infrastructureRepository.save(infrac);

		return infrac;
	}

	@Transactional
	public Infrastructure postIndustry(String industryName, String district) {
		Infrastructure infrac = new Infrastructure();
		District districts = districtRepository.findByDistrict(district);
		if (districts == null) {
			throw new NotFoundException("District with name " + district + " not found.");
		}
		Infrastructure infracs = infrastructureRepository.findByIndustries(industryName);
		if (infracs != null) {
			throw new AlreadyExistException("Industry with name " + industryName + " already exist.");
		}
		infrac.setDistrict(districts);
		infrac.setIndustries(industryName);
		State state = stateRepository.findById(districts.getState().getId());
		infrac.setState(state);
		infrastructureRepository.save(infrac);

		return infrac;

	}

	@Transactional
	public Infrastructure postHydropower(String hydropowerName, String district,String capacity) {
		Infrastructure infrac = new Infrastructure();
		District districts = districtRepository.findByDistrict(district);
		if (districts == null) {
			throw new NotFoundException("District with name " + district + " not found.");
		}
		Infrastructure infracs = infrastructureRepository.findByHydropower(hydropowerName);
		if (infracs != null) {
			throw new AlreadyExistException("Hydropower with name " + hydropowerName + " already exist.");
		}
		infrac.setDistrict(districts);
		infrac.setCapacity(capacity);
		infrac.setHydropower(hydropowerName);
		State state = stateRepository.findById(districts.getState().getId());
		infrac.setState(state);
		infrastructureRepository.save(infrac);

		return infrac;
	}

	@Transactional
	public Infrastructure postHospital(String hospitalName, String district, String hospitalContactNumber) {
		Infrastructure infrac = new Infrastructure();
		District districts = districtRepository.findByDistrict(district);
		if (districts == null) {
			throw new NotFoundException("District with name " + district + " not found.");
		}
		Infrastructure infracs = infrastructureRepository.findByHospital(hospitalName);
		if (infracs != null) {
			throw new AlreadyExistException("Hospital with name " + hospitalName + " already exist.");
		}
		infrac.setDistrict(districts);
		infrac.setHospital(hospitalName);
		infrac.setHospitalContactNumber(hospitalContactNumber);
		State state = stateRepository.findById(districts.getState().getId());
		infrac.setState(state);
		infrastructureRepository.save(infrac);

		return infrac;
	}

	@Transactional
	public List<AirportsResponseDto> getAirports(String state) {

		State states = stateRepository.findByState(state);
		List<AirportsResponseDto> airportsResponseDtoList = new ArrayList<AirportsResponseDto>();
		List<Infrastructure> infrastructures = infrastructureRepository.findAllByState(new State(states.getId()));
		infrastructures.stream().forEach(u -> {
			if (u.getAirports() != null) {
				AirportsResponseDto airportsResponseDto = new AirportsResponseDto();
				airportsResponseDto.setAirport(u.getAirports());
				District district = districtRepository.findById(u.getDistrict().getId());
				airportsResponseDto.setDisrict(district.getDistrict());
				airportsResponseDtoList.add(airportsResponseDto);
			}

		});
		return airportsResponseDtoList;

	}

	@Transactional
	public List<IndustriesResponseDto> getIndustries(String state) {
		State states = stateRepository.findByState(state);
		List<Infrastructure> infrastructures = infrastructureRepository.findAllByState(new State(states.getId()));
		List<IndustriesResponseDto> industriesResponseDtoList = new ArrayList<IndustriesResponseDto>();
		infrastructures.stream().forEach(u -> {
			if (u.getIndustries() != null) {
				IndustriesResponseDto industriesResponseDto = new IndustriesResponseDto();
				industriesResponseDto.setIndustry(u.getIndustries());
				District district = districtRepository.findById(u.getDistrict().getId());
				industriesResponseDto.setDistrict(district.getDistrict());
				industriesResponseDtoList.add(industriesResponseDto);
			}
		});

		return industriesResponseDtoList;

	}

	@Transactional
	public List<HydropowerResponseDto> getHydropower(String state) {
		State states = stateRepository.findByState(state);
		List<Infrastructure> infrastructures = infrastructureRepository.findAllByState(new State(states.getId()));
		List<HydropowerResponseDto> hydropowerResponseDtoList = new ArrayList<HydropowerResponseDto>();
		infrastructures.stream().forEach(u -> {
			if (u.getHydropower() != null) {
				HydropowerResponseDto hydropowerResponseDto = new HydropowerResponseDto();
				hydropowerResponseDto.setHydropower(u.getHydropower());
				District district = districtRepository.findById(u.getDistrict().getId());
				hydropowerResponseDto.setDistrict(district.getDistrict());
				hydropowerResponseDto.setCapacity(u.getCapacity());
				hydropowerResponseDtoList.add(hydropowerResponseDto);
			}
		});

		return hydropowerResponseDtoList;
	}

	@Transactional
	public List<HospitalResponseDto> getHospitalDetail(String state) {
		State states = stateRepository.findByState(state);
		List<Infrastructure> infrastructures = infrastructureRepository.findAllByState(new State(states.getId()));
		List<HospitalResponseDto> hospitalResponseDtosList = new ArrayList<HospitalResponseDto>();
		infrastructures.stream().forEach(u -> {
			if (u.getHospital() != null) {
				HospitalResponseDto hospitalResponseDto = new HospitalResponseDto();
				hospitalResponseDto.setHospital(u.getHospital());
				District district = districtRepository.findById(u.getDistrict().getId());
				hospitalResponseDto.setDistrict(district.getDistrict());
				hospitalResponseDto.setHospitalContactNumber(u.getHospitalContactNumber());
				hospitalResponseDtosList.add(hospitalResponseDto);
			}
		});

		return hospitalResponseDtosList;
	}

}

