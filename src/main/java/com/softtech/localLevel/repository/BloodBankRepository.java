package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.BloodBank;
import com.softtech.localLevel.model.State;
import com.softtech.localLevel.util.Status;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {

	List<BloodBank> findAllByStateAndStatusNot(State state, Status deleted);

}
