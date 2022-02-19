package com.ankur.keepurl.manager.api.mapper;

import org.springframework.stereotype.Service;

import com.ankur.keepurl.app.util.URLUtility;
import com.ankur.keepurl.dataaccess.document.UserLink;
import com.ankur.keepurl.manager.model.UserLinkDto;

@Service
public class UserLinkMapper {

	public UserLinkDto mapEntityToDto(UserLink entity) {
		if (entity == null) {
			return null;
		}
		UserLinkDto dto = new UserLinkDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setUrl(entity.getUrl());
		return dto;
	}
	
	public UserLink mapDtoToEntity(UserLinkDto dto) {
		return mapDtoToEntity(dto, new UserLink());
	}
	
	public UserLink mapDtoToEntity(UserLinkDto dto, UserLink entity) {
		if (dto == null) {
			return null;
		}
		entity.setId(dto.getId());
		if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
			entity.setTitle(URLUtility.fetchTitle(dto.getUrl()));
		} else {
			entity.setTitle(dto.getTitle());
		}
		entity.setUrl(dto.getUrl());
		return entity;
	}
}
