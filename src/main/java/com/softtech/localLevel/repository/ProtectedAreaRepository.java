package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.ProtectedAreas;
import com.softtech.localLevel.model.State;

@Repository
public interface ProtectedAreaRepository extends JpaRepository<ProtectedAreas, Long> {

	ProtectedAreas findByProtectedAreas(String string);

	List<ProtectedAreas> findAllByState(State state);

}
