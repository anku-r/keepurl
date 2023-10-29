package com.ankur.keepurl.service;

import java.util.List;

import com.ankur.keepurl.dataaccess.document.UserLink;
import com.ankur.keepurl.dto.TrashDTO;

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
     * @param Logged   in user
     */
    public void restore(String id, String user);

    /**
     * @return List of all links in trash
     * @param Logged in user
     */
    public List<TrashDTO> getAllLinks(String user);

    /**
     * Delete a link permanently from Trash
     * 
     * @param id
     * @param Logged in user
     */
    public void delete(String id, String user);

    /**
     * This removes all links from trash which are present for 5 days
     */
    public void trashCleanup();
}
