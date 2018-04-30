package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Ministry;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.util.GovType;
import com.softtech.localLevel.util.Status;

@Repository
public interface ministryRepository extends JpaRepository<Ministry,Long>{

	

	List<Ministry> findByGovTypeAndStatusNot(GovType central, Status deleted);

	Ministry findByMinistryNameAndGovType(String ministryName, GovType central);

	Ministry findByMinistryNameAndStateAndGovTypeAndStatusNot(String ministryName, State state, GovType local,
			Status deleted);

	Ministry findByMinistryNameAndState(String ministryName, State state);

	Ministry findByMinistryNameAndGovTypeAndStatusNot(String ministryName, GovType central, Status deleted);

	Ministry findByMinistryName(String ministryName);

	List<Ministry> findByGovTypeAndStatusNotAndState(GovType local, Status deleted, State state);

	Ministry findByMinistryNameAndGovTypeAndState(String ministryName, GovType local, State state);

	Ministry findByMinistryNameAndGovTypeAndStateAndStatusNot(String ministryName, GovType local, State state,
			Status deleted);

	Ministry findByMinistryNameAndStatusNot(String ministryName, Status deleted);

	Ministry findByMinistryNameAndStatusNotAndState(String ministryName, Status deleted, State state);


	List<Ministry> findAllByGovType(GovType central);
	

}
