package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.NewVdc;
import com.softtech.localLevel.model.State;


@Repository
public interface DistrictRepository extends JpaRepository <District,Long> {

	List<District> findAllByState(State state);

	District findByDistrict(String district);

	

	
}
