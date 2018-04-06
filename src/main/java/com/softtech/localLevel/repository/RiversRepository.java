package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;

import com.softtech.localLevel.model.Rivers;
import com.softtech.localLevel.model.State;

@Repository
public interface RiversRepository extends JpaRepository<Rivers, Long>{

	Rivers findByRiver(String river_name);

	List<Rivers> findAllByState(State state);

}
