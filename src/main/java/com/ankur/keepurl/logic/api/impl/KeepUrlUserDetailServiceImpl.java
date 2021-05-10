package com.ankur.keepurl.logic.api.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ankur.keepurl.dataaccess.entity.UserAccess;
import com.ankur.keepurl.dataaccess.repository.UserAccessRepository;
import com.ankur.keepurl.logic.api.KeepUrlUserDetailService;
import com.ankur.keepurl.logic.api.mapper.UserDetailMapper;
import com.ankur.keepurl.logic.exception.UsernameNotFoundException;

@Service
public class KeepUrlUserDetailServiceImpl implements KeepUrlUserDetailService {

	@Autowired
	private UserAccessRepository repository;
	
	@Autowired
	private UserDetailMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		Optional<UserAccess> userAccess = repository.findByUsername(username);
		if (!userAccess.isPresent()) {
			throw new UsernameNotFoundException();
		}
		return mapper.mapEntityToUserDetail(userAccess.get());
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<UserDetails> loadAllUsers() {	
		
		return repository.findAll().stream()
					.map(mapper::mapEntityToUserDetail)
					.collect(Collectors.toList());
	}
	
}
