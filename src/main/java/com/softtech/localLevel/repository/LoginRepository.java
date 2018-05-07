package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.Login;
import com.softtech.localLevel.util.Status;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

	Login findByUsernameAndStatusNot(String username, Status deleted);

}
