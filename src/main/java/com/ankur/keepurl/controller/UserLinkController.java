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

import com.ankur.keepurl.dto.UserLinkDTO;
import com.ankur.keepurl.exception.UrlDetailAlreadyExistException;
import com.ankur.keepurl.service.UserLinkService;

@RestController
@RequestMapping("api/userlinks")
public class UserLinkController {

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
