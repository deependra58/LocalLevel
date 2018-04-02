package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Metropolitan;

@Repository
public interface MetropolitanRepository extends JpaRepository<Metropolitan,Long>{

	List<Metropolitan> findAllByDistrict(District district);

	Metropolitan findByMetropolitan(String metropolitan);

}
