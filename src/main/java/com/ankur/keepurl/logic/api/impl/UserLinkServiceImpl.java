package com.ankur.keepurl.logic.api.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ankur.keepurl.app.util.AppConstants;
import com.ankur.keepurl.dataaccess.entity.UserLink;
import com.ankur.keepurl.dataaccess.repository.UserLinkRepository;
import com.ankur.keepurl.logic.api.UserLinkService;
import com.ankur.keepurl.logic.api.mapper.UserLinkMapper;
import com.ankur.keepurl.logic.api.model.UserLinkDto;
import com.ankur.keepurl.logic.exception.KeepUrlServiceException;
import com.ankur.keepurl.logic.exception.RequestNotFoundException;
import com.ankur.keepurl.logic.exception.UrlDetailAlreadyExistException;

@Service
public class UserLinkServiceImpl implements UserLinkService, AppConstants {

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
	public UserLinkDto getURLById(Long id) {
		
		Optional<UserLink> userLink = repository.findById(id);
		if (!userLink.isPresent()) {
			throw new RequestNotFoundException();
		}
		return mapper.mapEntityToDto(userLink.get());
	}

	@Override
	public UserLinkDto getURLByName(String title) {
		
		Optional<UserLink> userLink = repository.findByTitle(title);
		if (!userLink.isPresent()) {
			throw new RequestNotFoundException(URL_NOTFOUND_MSG);
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
				throw new RequestNotFoundException(URL_NOTFOUND_MSG);
			}
			UserLink userLink = repository.save(mapper
					.mapDtoToEntity(userLinkDto, userLinkOpt.get()));
			return mapper.mapEntityToDto(userLink);		
		} else {
			throw new KeepUrlServiceException(URL_ID_NOTFOUND_MSG);
		}
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteUrl(Long id) {
		
		Optional<UserLink> userLink = repository.findById(id);
		if (!userLink.isPresent()) {
			throw new RequestNotFoundException();
		}
		repository.delete(userLink.get());
	}

}
