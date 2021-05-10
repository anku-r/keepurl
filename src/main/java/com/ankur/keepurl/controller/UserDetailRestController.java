package com.ankur.keepurl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankur.keepurl.logic.api.KeepUrlUserDetailService;

@RestController
@RequestMapping("${keepurl.userdetail.endpoint}")
public class UserDetailRestController {

	@Autowired
	private KeepUrlUserDetailService service;
	
	@GetMapping
	public List<UserDetails> loadAllUsers() {
		return service.loadAllUsers();
	}
}
