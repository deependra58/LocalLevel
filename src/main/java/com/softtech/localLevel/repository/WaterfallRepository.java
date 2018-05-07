package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.State;
import com.softtech.localLevel.model.Waterfall;

@Repository
public interface WaterfallRepository extends JpaRepository<Waterfall, Long> {

	Waterfall findByWaterfall(String string);

	List<Waterfall> findAllByState(State state);

}
