package com.ankur.keepurl.dataaccess.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ankur.keepurl.dataaccess.document.UserRole;

public interface UserRoleRepository extends MongoRepository<UserRole, String> {

}
