package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.NewVdc;
import com.softtech.localLevel.model.OldVdc;

@Repository
public interface OldVdcRepository extends JpaRepository<OldVdc, Long>{

	List<OldVdc> findAllByNewVdc(NewVdc newVdc);

	OldVdc findByOldVdc(String oldVdc);

	Long findByNewVdc(NewVdc newVdc);



}
