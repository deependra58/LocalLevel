package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Municipality;
import com.softtech.localLevel.util.Status;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {

	List<Municipality> findAllBydistrict(District district);

	Municipality findByMunicipalAndStatusNot(String municipality, Status deleted);

	Municipality findByMunicipal(String municipality);

	Municipality findById(Long id);

	Municipality findByIdAndStatusNot(Long id, Status deleted);

}
