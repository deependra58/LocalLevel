package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.PoliceStation;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.util.Status;

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation, Long>{

	List<PoliceStation> findAllByStateAndStatusNot(State state, Status deleted);

}
