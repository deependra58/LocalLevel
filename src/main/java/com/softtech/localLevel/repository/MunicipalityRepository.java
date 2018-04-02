package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.Municipality;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality,Long>{

	List<Municipality> findAllBydistrict(District district);

	//Municipality findByMunicipality(String municipality);

	Municipality findByMunicipal(String municipality);

}
