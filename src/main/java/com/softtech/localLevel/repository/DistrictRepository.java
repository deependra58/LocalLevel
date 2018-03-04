package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.District;


@Repository
public interface DistrictRepository extends JpaRepository <District,Long> {

}
