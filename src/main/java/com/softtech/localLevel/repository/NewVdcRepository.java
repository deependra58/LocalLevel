package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.NewVdc;

@Repository
public interface NewVdcRepository extends JpaRepository <NewVdc, Long>{

}
