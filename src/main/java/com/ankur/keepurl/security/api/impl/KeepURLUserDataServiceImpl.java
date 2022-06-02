package com.ankur.keepurl.security.api.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ankur.keepurl.app.exception.KeepUrlServiceException;
import com.ankur.keepurl.app.util.AppConstants;
import com.ankur.keepurl.dataaccess.document.UserData;
import com.ankur.keepurl.dataaccess.repository.UserDataRepository;
import com.ankur.keepurl.security.api.KeepURLUserDataService;
import com.ankur.keepurl.security.api.mapper.UserDetailMapper;
import com.ankur.keepurl.security.model.UserDataDTO;

@Service
public class KeepURLUserDataServiceImpl implements KeepURLUserDataService {

    @Autowired
    private UserDataRepository repository;

    @Autowired
    private UserDetailMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserData> userData = repository.findById(username);
        if (!userData.isPresent()) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return mapper.mapEntityToUserDetail(userData.get());
    }

    @Override
    public void addUser(UserDataDTO userData) {
        if (!userData.getPassword().equals(userData.getConfirmPassword())) {
            throw new KeepUrlServiceException(AppConstants.PASS_NOT_MATCH);
        }
        if (repository.existsById(userData.getEmail())) {
            throw new KeepUrlServiceException(HttpStatus.CONFLICT, "Email Id is already registered");
        } 
        repository.save(mapper.mapDtoToEntity(userData));
    }
}