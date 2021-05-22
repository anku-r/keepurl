package com.ankur.keepurl.manager.api;

import java.util.List;

import com.ankur.keepurl.logic.api.model.UserLinkDto;

public interface UserLinkService {

	public List<UserLinkDto> getAllURLs();
	
	public UserLinkDto getURLById(Long id);
	
	public UserLinkDto getURLByName(String title);
	
	public UserLinkDto createUrl(UserLinkDto userLinkDto);
	
	public UserLinkDto updateUrl(UserLinkDto userLinkDto);
	
	public void deleteUrl(Long id);

}
