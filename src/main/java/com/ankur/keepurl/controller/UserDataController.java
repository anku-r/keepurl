package com.ankur.keepurl.controller;

import javax.validation.Valid;

import com.ankur.keepurl.dto.UserDataDTO;
import com.ankur.keepurl.service.KeepURLUserDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/userdata")
public class UserDataController {

    @Autowired
    private KeepURLUserDataService service;
    
    @PostMapping("register")
    public String register(@Valid @RequestBody UserDataDTO userData) {
        service.addUser(userData);
        return "User created. Please login";
    }
}
