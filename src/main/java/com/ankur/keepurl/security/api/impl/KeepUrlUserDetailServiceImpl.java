package com.ankur.keepurl.security.api.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ankur.keepurl.app.exception.UsernameNotFoundException;
import com.ankur.keepurl.dataaccess.entity.UserAccess;
import com.ankur.keepurl.dataaccess.repository.UserAccessRepository;
import com.ankur.keepurl.security.api.mapper.UserDetailMapper;

@Service
public class KeepUrlUserDetailServiceImpl implements UserDetailsService {

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
	
}
