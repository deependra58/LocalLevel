package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.SubMetropolitan;
import com.softtech.localLevel.util.Status;

public interface SubMetropolitanRepository extends JpaRepository<SubMetropolitan,Long>{

	//List<SubMetropolitan> findAllByDistricts(District district);

	List<SubMetropolitan> findAllByDistrict(District district);

	//SubMetropolitan findBySubMetropolitan(String subMetropolitan);

	SubMetropolitan findBySubMetropolitanAndStatusNot(String subMetropolitan, Status deleted);

}
