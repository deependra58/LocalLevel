package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.RuralMunicipality;
import com.softtech.localLevel.util.Status;

@Repository
public interface RuralMunicipalityRepository extends JpaRepository<RuralMunicipality,Long> {

	List<RuralMunicipality> findAllByDistrict(District district);

	

	RuralMunicipality findByRuralMunicipalAndStatusNot(String ruralMunicipality, Status deleted);



	RuralMunicipality findByRuralMunicipal(String ruralMunicipal);

}
