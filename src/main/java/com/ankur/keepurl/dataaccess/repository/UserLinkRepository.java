package com.ankur.keepurl.dataaccess.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ankur.keepurl.dataaccess.document.UserLink;

public interface UserLinkRepository extends MongoRepository<UserLink, String> {

	List<UserLink> findByUser(String user);
	
	Optional<UserLink> findByIdAndUser(String id, String user);
	
	Boolean existsByIdAndUser(String id, String user);
}
