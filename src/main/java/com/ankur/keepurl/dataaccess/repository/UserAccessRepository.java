package com.ankur.keepurl.dataaccess.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ankur.keepurl.dataaccess.document.UserAccess;

public interface UserAccessRepository extends MongoRepository<UserAccess, String> {

	Optional<UserAccess> findByUsername(String userName);
}
