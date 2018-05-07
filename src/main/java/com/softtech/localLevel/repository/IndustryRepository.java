package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Industry;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.util.Status;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long>{

	Industry findByIndustryAndStatusNot(String string, Status deleted);

	List<Industry> findAllByStateAndStatusNot(State state, Status deleted);

}
