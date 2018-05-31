package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.CitizenCharter;
import com.softtech.localLevel.util.Status;

@Repository
public interface CitizenCharterRepository extends JpaRepository<CitizenCharter, Long> {

	CitizenCharter findById(Long id);

	CitizenCharter findByRuralMunicipalityIdAndStatusNot(Long id, Status deleted);

	CitizenCharter findByMunicipalityIdAndStatusNot(Long id, Status deleted);

	CitizenCharter findByMetropolitanIdAndStatusNot(Long id, Status deleted);

	CitizenCharter findBySubMetropolitanIdAndStatusNot(Long id, Status deleted);

}
