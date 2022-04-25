package com.ankur.keepurl.security.api.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ankur.keepurl.app.exception.UsernameNotFoundException;
import com.ankur.keepurl.dataaccess.document.UserData;
import com.ankur.keepurl.dataaccess.repository.UserDataRepository;
import com.ankur.keepurl.security.api.mapper.UserDetailMapper;

@Service
public class KeepUrlUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserDataRepository repository;
	
	@Autowired
	private UserDetailMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		Optional<UserData> userData = repository.findById(username);
		if (!userData.isPresent()) {
			throw new UsernameNotFoundException();
		}	
		return mapper.mapEntityToUserDetail(userData.get());
	}
	
}
