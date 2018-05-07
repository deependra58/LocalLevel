package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.softtech.localLevel.model.Ward;

@Repository
public interface WardRepository extends JpaRepository<Ward,Long> {

	

}
