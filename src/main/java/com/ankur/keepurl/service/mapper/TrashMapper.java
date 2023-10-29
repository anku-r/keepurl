package com.ankur.keepurl.service.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.ankur.keepurl.dataaccess.document.Trash;
import com.ankur.keepurl.dataaccess.document.UserLink;
import com.ankur.keepurl.dto.TrashDTO;
import com.ankur.keepurl.dto.UserLinkDTO;

@Service
public class TrashMapper {

    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Trash mapLinkToTrash(UserLink userLink) {
	if (userLink == null) {
	    return null;
	}
	Trash trashLink = new Trash();
	trashLink.setId(userLink.getId());
	trashLink.setTitle(userLink.getTitle());
	trashLink.setTitleShort(userLink.getTitleShort());
	trashLink.setUrl(userLink.getUrl());
	trashLink.setDate(LocalDate.now());
	trashLink.setUser(userLink.getUser());
	return trashLink;
    }

    public UserLinkDTO mapEntityToLinkDto(Trash entity) {
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

    public TrashDTO mapEntityToDto(Trash entity) {
	if (entity == null) {
	    return null;
	}
	TrashDTO dto = new TrashDTO();
	dto.setId(entity.getId());
	dto.setTitle(entity.getTitle());
	dto.setTitleShort(entity.getTitleShort());
	dto.setUrl(entity.getUrl());
	dto.setDate(entity.getDate().format(format));
	dto.setUser(entity.getUser());
	return dto;
    }
}
