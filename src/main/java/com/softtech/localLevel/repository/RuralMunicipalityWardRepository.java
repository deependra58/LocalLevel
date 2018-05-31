package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtech.localLevel.model.RuralMunicipalWard;
import com.softtech.localLevel.model.RuralMunicipality;
import com.softtech.localLevel.util.Status;

/**
 * <<This is the repository for RuralMunicipality Ward>>
 * 
 * @author Deependra
 * @since 9 May 2018
 * @version 1.0.1
 */

public interface RuralMunicipalityWardRepository extends JpaRepository<RuralMunicipalWard, Long> {

	List<RuralMunicipalWard> findAllByRuralMunicipalityAndNewWardIdAndStatusNot(RuralMunicipality ruralMunicipal,
			Long newWardId, Status deleted);

	RuralMunicipalWard findByOldVdcNameAndOldWardIdAndStatusNot(String oldVdcName, Long oldWardId, Status deleted);

	RuralMunicipalWard findByIdAndStatusNot(Long id, Status deleted);

}
