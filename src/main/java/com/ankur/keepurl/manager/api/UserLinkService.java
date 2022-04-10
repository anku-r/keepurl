package com.ankur.keepurl.manager.api;

import java.util.List;

import com.ankur.keepurl.manager.model.UserLinkDTO;

public interface UserLinkService {

	public List<UserLinkDTO> getAllURLs();
	
	public UserLinkDTO getURLById(String id);
	
	public UserLinkDTO createUrl(UserLinkDTO userLinkDto);
	
	public UserLinkDTO updateUrl(UserLinkDTO userLinkDto);
	
	public void deleteUrl(String id);

}
