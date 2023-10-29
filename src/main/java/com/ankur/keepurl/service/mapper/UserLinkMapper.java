package com.ankur.keepurl.service.mapper;

import org.springframework.stereotype.Service;

import com.ankur.keepurl.dataaccess.document.UserLink;
import com.ankur.keepurl.dto.UserLinkDTO;
import com.ankur.keepurl.utility.URLUtility;

@Service
public class UserLinkMapper {

    public UserLinkDTO mapEntityToDto(UserLink entity) {
	if (entity == null) {
	    return null;
	}
	UserLinkDTO dto = new UserLinkDTO();
	dto.setId(entity.getId());
	dto.setTitle(entity.getTitle());
	dto.setTitleShort(entity.getTitleShort());
	dto.setUrl(entity.getUrl());
	dto.setUser(entity.getUser());
	return dto;
    }

    public UserLink mapDtoToEntity(UserLinkDTO dto) {
	return mapDtoToEntity(dto, new UserLink());
    }

    public UserLink mapDtoToEntity(UserLinkDTO dto, UserLink entity) {
	if (dto == null) {
	    return null;
	}
	entity.setId(dto.getId());
	if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
	    entity.setTitle(URLUtility.fetchTitle(dto.getUrl()));
	} else {
	    entity.setTitle(dto.getTitle());
	}
	entity.setTitleShort(URLUtility.minimizeTitle(entity.getTitle()));
	entity.setUrl(dto.getUrl());
	entity.setUser(dto.getUser());
	return entity;
    }
}
