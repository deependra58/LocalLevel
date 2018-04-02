package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Vdc;
import com.softtech.localLevel.util.Status;

@Repository
public interface VdcRepository extends JpaRepository<Vdc, Long> {

	Vdc findByIdAndStatusNot(Long vdcId, Status deleted);

	Vdc findByIdAndVdcAndStatusNot(Long vdcId, String vdc, Status deleted);

}
