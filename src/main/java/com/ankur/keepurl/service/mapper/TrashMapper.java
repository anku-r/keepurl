package com.ankur.keepurl.service.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.ankur.keepurl.dataaccess.document.Trash;
import com.ankur.keepurl.dataaccess.document.UserLink;
import com.ankur.keepurl.dto.TrashDTO;

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

    public UserLink mapTrashToLink(Trash trashLink) {
	if (trashLink == null) {
	    return null;
	}
	UserLink userLink = new UserLink();
	userLink.setId(trashLink.getId());
	userLink.setTitle(trashLink.getTitle());
	userLink.setTitleShort(trashLink.getTitleShort());
	userLink.setUrl(trashLink.getUrl());
	userLink.setUser(trashLink.getUser());
	return userLink;
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
