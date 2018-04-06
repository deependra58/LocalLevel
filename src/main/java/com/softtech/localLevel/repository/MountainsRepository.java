package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Mountains;
import com.softtech.localLevel.model.State;

@Repository
public interface MountainsRepository extends JpaRepository<Mountains,Long> {

	Mountains findByMountain(String string);

	List<Mountains> findAllByState(State state);

}
