package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Vdc;

@Repository
public interface VdcRepository extends JpaRepository<Vdc,Long>{

}
