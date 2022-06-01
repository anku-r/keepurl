package com.ankur.keepurl.security.api;

import com.ankur.keepurl.security.model.UserDataDTO;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface KeepURLUserDataService extends UserDetailsService {

    public void addUser(UserDataDTO userData);
}
