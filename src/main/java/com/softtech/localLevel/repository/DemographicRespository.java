package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Demographic;

@Repository
public interface DemographicRespository extends JpaRepository<Demographic,Long> {

}
