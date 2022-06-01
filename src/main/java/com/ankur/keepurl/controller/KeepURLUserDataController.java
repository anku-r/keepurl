package com.ankur.keepurl.controller;

import javax.validation.Valid;

import com.ankur.keepurl.security.api.KeepURLUserDataService;
import com.ankur.keepurl.security.model.UserDataDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/userdata")
public class KeepURLUserDataController {

    @Autowired
    private KeepURLUserDataService service;
    
    @PostMapping("register")
    public void register(@Valid @RequestBody UserDataDTO userData) {
        service.addUser(userData);
    }
}
