package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Hydropower;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.util.Status;

@Repository
public interface HydropowerRepository extends JpaRepository<Hydropower, Long> {

	Hydropower findByHydropowerAndStatusNot(String string, Status deleted);

	List<Hydropower> findAllByStateAndStatusNot(State state, Status deleted);

}
