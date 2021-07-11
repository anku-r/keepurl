package com.ankur.keepurl.manager.api.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ankur.keepurl.app.exception.KeepUrlServiceException;
import com.ankur.keepurl.app.exception.RequestNotFoundException;
import com.ankur.keepurl.app.exception.UrlDetailAlreadyExistException;
import com.ankur.keepurl.app.util.AppConstants;
import com.ankur.keepurl.dataaccess.document.UserLink;
import com.ankur.keepurl.dataaccess.repository.UserLinkRepository;
import com.ankur.keepurl.logic.manager.model.UserLinkDto;
import com.ankur.keepurl.manager.api.UserLinkService;
import com.ankur.keepurl.manager.api.mapper.UserLinkMapper;

@Service
@Transactional
public class UserLinkServiceImpl implements UserLinkService {

	@Autowired
	private UserLinkRepository repository;
	
	@Autowired
	private UserLinkMapper mapper;

	@Override
	public List<UserLinkDto> getAllURLs() {
		
		List<UserLink> userLinks = repository.findAll();
		return userLinks.stream().map(mapper::mapEntityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public UserLinkDto getURLById(String id) {
		
		Optional<UserLink> userLink = repository.findById(id);
		if (!userLink.isPresent()) {
			throw new RequestNotFoundException();
		}
		return mapper.mapEntityToDto(userLink.get());
	}

	@Override
	public UserLinkDto createUrl(UserLinkDto userLinkDto) {	
		
		try {
			UserLink userLink = repository.save(mapper.mapDtoToEntity(userLinkDto));
			return mapper.mapEntityToDto(userLink);
		} catch (DataIntegrityViolationException e) {
			throw new UrlDetailAlreadyExistException();
		}
	}

	@Override
	public UserLinkDto updateUrl(UserLinkDto userLinkDto) {
		
		if (userLinkDto.getId() != null) {
			
			Optional<UserLink> userLinkOpt = repository.findById(userLinkDto.getId());
			if (!userLinkOpt.isPresent()) {
				throw new RequestNotFoundException(AppConstants.URL_NOTFOUND_MSG);
			}
			UserLink userLink = repository.save(mapper
					.mapDtoToEntity(userLinkDto, userLinkOpt.get()));
			return mapper.mapEntityToDto(userLink);		
		} else {
			throw new KeepUrlServiceException(AppConstants.URL_ID_NOTFOUND_MSG);
		}
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteUrl(String id) {
		
		Optional<UserLink> userLink = repository.findById(id);
		if (!userLink.isPresent()) {
			throw new RequestNotFoundException(AppConstants.URL_NOTFOUND_MSG);
		}
		repository.delete(userLink.get());
	}

}
