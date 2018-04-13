package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.District;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.util.Status;


@Repository
public interface DistrictRepository extends JpaRepository <District,Long> {

	List<District> findAllByState(State state);

	District findByDistrict(String district);

	Long findByState(State state);

	District findById(Long districtId);

	District findBydistrict(String districts);

	District findByDistrictAndStatusNot(String mountainDistrict, Status deleted);

	District findById(District district);

	

	

	
}
