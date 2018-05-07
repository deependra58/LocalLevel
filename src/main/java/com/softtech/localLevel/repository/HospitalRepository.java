package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Hospital;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.util.Status;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	Hospital findByHospitalAndStatusNot(String string, Status deleted);

	List<Hospital> findAllByStateAndStatusNot(State state, Status deleted);

}
