package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Infrastructure;
import com.softtech.localLevel.model.State;

@Repository
public interface InfrastructureRepository extends JpaRepository<Infrastructure,Long> {


	Infrastructure findByIndustries(String industryName);

	Infrastructure findByHydropower(String hydropowerName);

	Infrastructure findByHospital(String hospitalName);

	Infrastructure findByAirports(String airportName);

	List<Infrastructure> findAllByState(State state);

}
