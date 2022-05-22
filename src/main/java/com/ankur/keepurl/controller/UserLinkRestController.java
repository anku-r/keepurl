package com.ankur.keepurl.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankur.keepurl.app.exception.UrlDetailAlreadyExistException;
import com.ankur.keepurl.manager.api.UserLinkService;
import com.ankur.keepurl.manager.model.UserLinkDTO;

@RestController
@RequestMapping("api/userlink")
public class UserLinkRestController {

    @Autowired
    private UserLinkService service;

    @GetMapping
    public List<UserLinkDTO> getURLs(Principal user) {
	return service.getAllURLs(user.getName());
    }

    @GetMapping("{id}")
    public UserLinkDTO getURL(@PathVariable("id") String id, Principal user) {
	return service.getURLById(id, user.getName());
    }

    @PostMapping
    public UserLinkDTO createURL(@Valid @RequestBody UserLinkDTO userLinkDto, Principal user) {
	return service.createUrl(userLinkDto, user.getName());
    }

    @PutMapping
    public UserLinkDTO updateURL(@Valid @RequestBody UserLinkDTO userLinkDto, Principal user) {
	try {
	    return service.updateUrl(userLinkDto, user.getName());
	} catch (DataIntegrityViolationException e) {
	    throw new UrlDetailAlreadyExistException();
	}
    }

    @DeleteMapping("{id}")
    public void deleteURL(@PathVariable("id") String id, Principal user) {
	service.deleteUrl(id, user.getName());
    }
}
