package com.ankur.keepurl.manager.api;

import java.util.List;

import com.ankur.keepurl.dataaccess.document.UserLink;
import com.ankur.keepurl.manager.model.TrashDTO;

public interface TrashService {

	/**
	 * Saves a user book mark into trash
	 * 
	 * @param userLink
	 */
	public void moveToTrash(UserLink userLink);
	
	/**
	 * Moves/Restore a book mark from trash into Userlink
	 * 
	 * @param UserLink Id
	 */
	public void restore(String id);
	
	/**
	 * @return List of all links in trash
	 */
	public List<TrashDTO> getAllLinks();
	
	/**
	 * Delete a link permanently from Trash
	 * 
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * This removes all links from trash which are present for 5 days
	 */
	public void trashCleanup();
}
