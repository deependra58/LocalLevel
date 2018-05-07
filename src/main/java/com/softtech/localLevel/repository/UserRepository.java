package com.softtech.localLevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.User;
import com.softtech.localLevel.util.Status;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailAndStatusNot(String email, Status deleted);

}
