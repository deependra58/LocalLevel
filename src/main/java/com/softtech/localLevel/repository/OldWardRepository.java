package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.OldWard;

@Repository
public interface OldWardRepository extends JpaRepository<OldWard, Long>{

}
