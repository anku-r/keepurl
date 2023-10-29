package com.ankur.keepurl.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ankur.keepurl.dto.UserDataDTO;

public interface KeepURLUserDataService extends UserDetailsService {

    public void addUser(UserDataDTO userData);
}
