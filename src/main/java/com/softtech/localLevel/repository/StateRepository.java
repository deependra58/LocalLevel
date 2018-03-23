package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.State;

@Repository
public interface StateRepository extends JpaRepository <State,Long> {

	//Long getId(String state);

	

	void findByState(State state);

	State findByState(String state);

	State findById(Object state);

	

}
