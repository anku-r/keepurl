package com.ankur.keepurl.dataaccess.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ankur.keepurl.dataaccess.document.Trash;

public interface TrashRepository extends MongoRepository<Trash, String>{
	
	/**
	 * @param date
	 * @return List of links deleted on certain date
	 */
	public List<Trash> findByDate(LocalDate date);
}
