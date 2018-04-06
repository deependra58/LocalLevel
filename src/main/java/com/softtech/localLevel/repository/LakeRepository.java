package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Lakes;
import com.softtech.localLevel.model.State;

@Repository
public interface LakeRepository extends JpaRepository<Lakes, Long> {

	Lakes findBylakes(String string);

	List<Lakes> findAllByState(State state);

}
