package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.LoginToken;

@Repository
public interface LoginTokenRepository extends JpaRepository<LoginToken, Long>{

}
