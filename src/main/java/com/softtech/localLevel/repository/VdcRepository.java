package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Vdc;
import com.softtech.localLevel.util.Status;

@Repository
public interface VdcRepository extends JpaRepository<Vdc, Long> {

	Vdc findByIdAndStatusNot(Long vdcId, Status deleted);

	Vdc findByIdAndVdcAndStatusNot(Long vdcId, String vdc, Status deleted);

	Vdc findByVdcAndStatusNot(String oldVdc, Status deleted);

	List<Vdc> findAllByVdcAndStatusNot(String oldVdc, Status deleted);

	List<Vdc> findAllByDistrict(District district);



	List<Vdc> findAllByRuralMunicipalityId(Long id);

	List<Vdc> findAllByMunicipalityId(Long id);

	List<Vdc> findAllBySubMetropolitanId(Long id);

	List<Vdc> findAllByMetropolitanId(Long id);



}
