package com.ankur.keepurl.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ankur.keepurl.annotation.Cached;
import com.ankur.keepurl.dataaccess.document.UserLink;
import com.ankur.keepurl.dataaccess.repository.UserLinkRepository;
import com.ankur.keepurl.dto.UserLinkDTO;
import com.ankur.keepurl.exception.KeepUrlServiceException;
import com.ankur.keepurl.exception.RequestNotFoundException;
import com.ankur.keepurl.exception.UrlDetailAlreadyExistException;
import com.ankur.keepurl.service.TrashService;
import com.ankur.keepurl.service.UserLinkService;
import com.ankur.keepurl.service.mapper.UserLinkMapper;
import com.ankur.keepurl.utility.AppConstants;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserLinkServiceImpl implements UserLinkService {

  
    private final UserLinkRepository repository;

    private final UserLinkMapper mapper;

    private final TrashService trashService;

    @Override
    @Cached(keyArgumentIndex = 0, databaseEntity = UserLink.class)
    public List<UserLinkDTO> getAllURLs(String user) {
	return repository.findByUser(user).stream()
		.map(mapper::mapEntityToDto)
		.collect(Collectors.toList());
    }

    @Override
    public UserLinkDTO getURLById(String id, String user) {
	Optional<UserLink> userLink = repository.findByIdAndUser(id, user);
	if (userLink.isEmpty()) {
	    throw new RequestNotFoundException();
	}
	return mapper.mapEntityToDto(userLink.get());
    }

    @Override
	public UserLinkDTO createUrl(UserLinkDTO userLinkDto, String user) {
	try {
	    userLinkDto.setUser(user);
	    UserLink userLink = repository.save(mapper.mapDtoToEntity(userLinkDto));
	    return mapper.mapEntityToDto(userLink);
	} catch (DataIntegrityViolationException e) {
	    throw new UrlDetailAlreadyExistException();
	}
    }

    @Override
    public UserLinkDTO updateUrl(UserLinkDTO userLinkDto, String user) {
	if (userLinkDto.getId() != null) {
	    userLinkDto.setUser(user);
	    Optional<UserLink> userLinkOpt = repository.findByIdAndUser(userLinkDto.getId(), user);
	    if (userLinkOpt.isEmpty()) {
		throw new RequestNotFoundException(AppConstants.URL_NOTFOUND_MSG);
	    }
	    UserLink userLink = mapper.mapDtoToEntity(userLinkDto, userLinkOpt.get());
		repository.save(userLink);
	    return mapper.mapEntityToDto(userLink);
	} else {
	    throw new KeepUrlServiceException(AppConstants.URL_ID_NOTFOUND_MSG);
	}
    }

    @Override
	public void deleteUrl(String id, String user) {
	Optional<UserLink> userLink = repository.findByIdAndUser(id, user);
	if (userLink.isEmpty()) {
	    throw new RequestNotFoundException(AppConstants.URL_NOTFOUND_MSG);
	}
	trashService.moveToTrash(user, userLink.get());
	repository.delete(userLink.get());
    }
}
