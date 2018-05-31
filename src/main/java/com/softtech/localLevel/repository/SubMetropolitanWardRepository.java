package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.SubMetropolitan;
import com.softtech.localLevel.model.SubMetropolitanWard;
import com.softtech.localLevel.util.Status;

/*
 * <<This is the repository for SubMetropolitan Repository>>
 * @Author Deependra
 * @Since 10, May 2018
 * @Version 1.0.1
 * 
 * */
@Repository
public interface SubMetropolitanWardRepository extends JpaRepository<SubMetropolitanWard, Long> {

	List<SubMetropolitanWard> findAllBySubMetropolitanAndNewWardIdAndStatusNot(SubMetropolitan subMetropolitans,
			Long newWardId, Status deleted);

	SubMetropolitanWard findByOldVdcNameAndOldWardIdAndStatusNot(String oldVdcName, Long oldWardId, Status deleted);

	SubMetropolitanWard findBySubMetropolitanAndStatusNot(Long id, Status deleted);

	SubMetropolitanWard findByIdAndStatusNot(Long id, Status deleted);

}
