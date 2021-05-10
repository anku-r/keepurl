package com.ankur.keepurl.logic.api;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface KeepUrlUserDetailService extends UserDetailsService {

	public List<UserDetails> loadAllUsers();
}
