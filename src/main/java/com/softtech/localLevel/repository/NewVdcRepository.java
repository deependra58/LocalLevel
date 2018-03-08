package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.District;
import com.softtech.localLevel.model.NewVdc;

@Repository
public interface NewVdcRepository extends JpaRepository <NewVdc, Long>{

	List<NewVdc> findAllByDistrict(District district);

	NewVdc findById(Long id);

	NewVdc findByNewVdc(String vdcName);

	

	/*List<NewVdc> findAllByDistrict(District district);

	NewVdc findById(Long id);

	NewVdc findByVdcName(String vdcName);*/

}
