package com.ankur.keepurl.dataaccess.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ankur.keepurl.dataaccess.document.UserData;

public interface UserDataRepository extends MongoRepository<UserData, String> {

}
