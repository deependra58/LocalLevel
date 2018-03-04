package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.NewWard;

@Repository
public interface NewWardRepository extends JpaRepository <NewWard, Long>{

}
