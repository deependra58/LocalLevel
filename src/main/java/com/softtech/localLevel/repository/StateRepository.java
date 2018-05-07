package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.util.Status;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	// Long getId(String state);

	State findByState(State state);

	State findByState(String state);

	State findById(Object state);

	void save(String state);

}
