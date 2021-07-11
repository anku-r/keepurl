package com.ankur.keepurl.dataaccess.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ankur.keepurl.dataaccess.document.UserLink;

public interface UserLinkRepository extends MongoRepository<UserLink, String> {

}
