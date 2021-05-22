package com.ankur.keepurl.manager.api;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

public interface KeepUrlUserDetailService {

	public List<UserDetails> loadAllUsers();
}
