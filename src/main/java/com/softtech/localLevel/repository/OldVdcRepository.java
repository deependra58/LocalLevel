package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.OldVdc;

@Repository
public interface OldVdcRepository extends JpaRepository<OldVdc, Long>{

}
