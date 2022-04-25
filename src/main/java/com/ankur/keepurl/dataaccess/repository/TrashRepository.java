package com.ankur.keepurl.dataaccess.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ankur.keepurl.dataaccess.document.Trash;

public interface TrashRepository extends MongoRepository<Trash, String>{
	
	/**
	 * @param date
	 * @return List of links deleted on certain date
	 */
	List<Trash> findByDateLessThanEqual(LocalDate date);
	
	/**
	 * @param user
	 * @return List of links in trash for a user
	 */
	List<Trash> findByUser(String user);
	
	/**
	 * 
	 * @param id
	 * @param user
	 * @return Link residing in trash by id for a user
	 */
	Optional<Trash> findByIdAndUser(String id, String user);
}
