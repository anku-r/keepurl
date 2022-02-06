package com.ankur.keepurl.manager.api;

import java.util.List;

import com.ankur.keepurl.manager.model.UserLinkDto;

public interface UserLinkService {

	public List<UserLinkDto> getAllURLs();
	
	public UserLinkDto getURLById(String id);
	
	public UserLinkDto createUrl(UserLinkDto userLinkDto);
	
	public UserLinkDto updateUrl(UserLinkDto userLinkDto);
	
	public void deleteUrl(String id);

}
