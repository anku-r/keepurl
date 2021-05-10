package com.ankur.keepurl.dataaccess.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankur.keepurl.dataaccess.entity.UserAccess;

public interface UserAccessRepository extends JpaRepository<UserAccess, Long> {

	Optional<UserAccess> findByUsername(String userName);
}
