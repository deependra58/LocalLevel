package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Attraction;
import com.softtech.localLevel.model.State;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {

	Attraction findByItem(String item);

	List<Attraction> findAllByState(State state);

}
