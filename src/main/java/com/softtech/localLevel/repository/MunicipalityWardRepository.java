package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.MunicipalWard;
import com.softtech.localLevel.model.Municipality;
import com.softtech.localLevel.model.RuralMunicipalWard;
import com.softtech.localLevel.util.Status;

/*This is the repository for municipality ward*/

@Repository
public interface MunicipalityWardRepository extends JpaRepository<MunicipalWard, Long>{

	List<MunicipalWard> findAllByMunicipalityAndNewWardIdAndStatusNot(Municipality municipal, Long newWardId,
			Status deleted);

	MunicipalWard findByOldVdcNameAndOldWardIdAndStatusNot(String oldVdcName, Long oldWardId, Status deleted);

	MunicipalWard findByIdAndStatusNot(Long id, Status deleted);


}



