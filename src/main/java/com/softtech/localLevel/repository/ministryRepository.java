package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Ministry;
import com.softtech.localLevel.util.GovType;
import com.softtech.localLevel.util.Status;

@Repository
public interface ministryRepository extends JpaRepository<Ministry,Long>{

	//Ministry findByMinistryName(String ministryName);

	//List<Ministry> findAllAndStatusNot(Status deleted);

//	List<Ministry> findByStatusNot(Status deleted);

	//List<Ministry> findByGovTypeAndStatusNot(GovType central, Status deleted);

	List<Ministry> findByGovTypeAndStatusNot(GovType central, Status deleted);

	Ministry findByMinistryNameAndGovType(String ministryName, GovType central);

	//Ministry findByMinistryNameAndStatusNot(String ministryName, Status deleted);

	Ministry findByMinistryNameAndGovTypeAndStatusNot(String ministryName, GovType central, Status deleted);
	

}
