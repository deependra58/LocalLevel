package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Metropolitan;
import com.softtech.localLevel.util.Status;

@Repository
public interface MetropolitanRepository extends JpaRepository<Metropolitan, Long> {

	List<Metropolitan> findAllByDistrict(District district);

	// Metropolitan findByMetropolitan(String metropolitan);

	Metropolitan findByMetropolitanAndStatusNot(String metropolitan, Status deleted);

	Metropolitan findByMetropolitan(String string);

	Metropolitan findById(Long metropolitan_id);

}
