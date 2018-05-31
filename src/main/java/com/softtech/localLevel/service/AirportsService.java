package com.softtech.localLevel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softech.localLevel.exception.AlreadyExistException;
import com.softech.localLevel.exception.NotFoundException;
import com.softtech.localLevel.model.Airports;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.repository.AirportsRepository;
import com.softtech.localLevel.repository.DistrictRepository;
import com.softtech.localLevel.repository.StateRepository;
import com.softtech.localLevel.response.AirportsResponseDto;
import com.softtech.localLevel.util.Status;

@Service
public class AirportsService {

	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private AirportsRepository airportsRepository;

	public Airports postAirports(String airportName, String districts, String description, String airportAddress) {
		Airports airports = new Airports();
		District district = districtRepository.findByDistrict(districts);
		State state = stateRepository.findById(district.getState().getId());
		Airports arpAirports=airportsRepository.findByAirportNameAndStatusNot(airportName, Status.DELETED);
		if(arpAirports!=null) {
			throw new AlreadyExistException("Airport with name "+airportName +" already exist");
		}
		airports.setState(state);
		airports.setDistrict(district);
		airports.setAirportName(airportName);
		airports.setAirportAddress(airportAddress);
		airports.setDescription(description);
		airports.setStatus(Status.ACTIVE);
		airportsRepository.save(airports);
		return airports;

	}

	public List<AirportsResponseDto> getAirports(String state) {
		State states = stateRepository.findByState(state);
		List<AirportsResponseDto> airportsResponseDtoList = new ArrayList<AirportsResponseDto>();
		List<Airports> infrastructures = airportsRepository.findAllByState(new State(states.getId()));
		infrastructures.stream().forEach(u -> {
			
				AirportsResponseDto airportsResponseDto = new AirportsResponseDto();
				airportsResponseDto.setAirportName(u.getAirportName());
				District district = districtRepository.findById(u.getDistrict().getId());
				airportsResponseDto.setAirportAddress(u.getAirportAddress());
				airportsResponseDto.setAirportImage(u.getAirportImage());
				airportsResponseDto.setDescription(u.getDescription());
				airportsResponseDto.setDisrict(district.getDistrict());
				airportsResponseDtoList.add(airportsResponseDto);
			

		});
		return airportsResponseDtoList;
	}

	@Transactional
	public void editAirport(String airportName, String district, String description, String airportAddress) {
		Airports airport=airportsRepository.findByAirportNameAndStatusNot(airportName, Status.DELETED);
		if(airport==null) {
			throw new NotFoundException("Airport with name "+airportName +" not found");
		}
		District districts = districtRepository.findByDistrict(district);
		State state = stateRepository.findById(districts.getState().getId());
		airport.setState(state);
		airport.setDistrict(districts);
		airport.setAirportName(airportName);
		airport.setAirportAddress(airportAddress);
		airport.setDescription(description);
		airport.setStatus(Status.ACTIVE);
		airportsRepository.save(airport);
		
		
		
	}
	
	@Transactional
	public void deleteAirport(String airportName) {
		Airports airport=airportsRepository.findByAirportNameAndStatusNot(airportName, Status.DELETED);
		if(airport==null) {
			throw new NotFoundException("Airport with name "+airportName+" not found.");
		}
		airport.setStatus(Status.DELETED);
		airportsRepository.save(airport);
	}

	

}
