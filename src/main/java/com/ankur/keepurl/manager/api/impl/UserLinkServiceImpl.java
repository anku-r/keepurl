package com.ankur.keepurl.manager.api.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ankur.keepurl.app.exception.KeepUrlServiceException;
import com.ankur.keepurl.app.exception.RequestNotFoundException;
import com.ankur.keepurl.app.exception.UrlDetailAlreadyExistException;
import com.ankur.keepurl.app.util.AppConstants;
import com.ankur.keepurl.dataaccess.document.UserLink;
import com.ankur.keepurl.dataaccess.repository.UserLinkRepository;
import com.ankur.keepurl.manager.api.TrashService;
import com.ankur.keepurl.manager.api.UserLinkService;
import com.ankur.keepurl.manager.api.mapper.UserLinkMapper;
import com.ankur.keepurl.manager.model.UserLinkDTO;

@Service
@Transactional
public class UserLinkServiceImpl implements UserLinkService {

	@Autowired
	private UserLinkRepository repository;
	
	@Autowired
	private UserLinkMapper mapper;
	
	@Autowired
	private TrashService trashService;

	@Override
	public List<UserLinkDTO> getAllURLs() {
		
		List<UserLink> userLinks = repository.findAll();
		return userLinks.stream().map(mapper::mapEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public UserLinkDTO getURLById(String id) {
		
		Optional<UserLink> userLink = repository.findById(id);
		if (!userLink.isPresent()) {
			throw new RequestNotFoundException();
		}
		return mapper.mapEntityToDto(userLink.get());
	}

	@Override
	public UserLinkDTO createUrl(UserLinkDTO userLinkDto) {	
		
		try {
			UserLink userLink = repository.save(mapper.mapDtoToEntity(userLinkDto));
			return mapper.mapEntityToDto(userLink);
		} catch (DataIntegrityViolationException e) {
			throw new UrlDetailAlreadyExistException();
		}
	}

	@Override
	public UserLinkDTO updateUrl(UserLinkDTO userLinkDto) {
		
		if (userLinkDto.getId() != null) {			
			Optional<UserLink> userLinkOpt = repository.findById(userLinkDto.getId());
			if (!userLinkOpt.isPresent()) {
				throw new RequestNotFoundException(AppConstants.URL_NOTFOUND_MSG);
			}
			UserLink userLink = mapper.mapDtoToEntity(userLinkDto, userLinkOpt.get());
			return mapper.mapEntityToDto(userLink);		
		} else {
			throw new KeepUrlServiceException(AppConstants.URL_ID_NOTFOUND_MSG);
		}
	}

	@Override
	public void deleteUrl(String id) {
		
		Optional<UserLink> userLink = repository.findById(id);
		if (!userLink.isPresent()) {
			throw new RequestNotFoundException(AppConstants.URL_NOTFOUND_MSG);
		}
		trashService.moveToTrash(userLink.get());
		repository.delete(userLink.get());
	}

}
