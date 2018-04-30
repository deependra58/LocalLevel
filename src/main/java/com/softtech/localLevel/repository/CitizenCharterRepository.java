package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.CitizenCharter;

@Repository
public interface CitizenCharterRepository extends JpaRepository<CitizenCharter, Long>{

	CitizenCharter findById(Long id);

}
