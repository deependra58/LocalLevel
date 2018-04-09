package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Airports;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.util.Status;

@Repository
public interface AirportsRepository extends JpaRepository<Airports, Long> {

	//Airports findByAirports(String string);

	Airports findByAirportName(String string);

	Airports findByAirportNameAndStatusNot(String string, Status deleted);

	List<Airports> findAllByState(State state);

}
