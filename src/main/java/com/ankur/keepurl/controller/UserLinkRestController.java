package com.ankur.keepurl.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ankur.keepurl.logic.api.model.UserLinkDto;
import com.ankur.keepurl.logic.exception.UrlDetailAlreadyExistException;
import com.ankur.keepurl.manager.api.UserLinkService;

@RestController
@RequestMapping("${keepurl.userlink.endpoint}")
public class UserLinkRestController {

	@Autowired
	private UserLinkService service;
	
	@GetMapping
	public List<UserLinkDto> listURLs() {
		return service.getAllURLs();
	}
	
	@GetMapping("{id}")
	public UserLinkDto getURL(@PathVariable("id") Long id) {
		
		return service.getURLById(id);
	}
	
	@GetMapping(params = "title", produces = MediaType.APPLICATION_XML_VALUE)
	public UserLinkDto getURL(@RequestParam("title") String title) {
		
		return service.getURLByName(title);
	}
	
	@PostMapping
	public UserLinkDto createURL(@Valid @RequestBody UserLinkDto userLinkDto) {
		
		return service.createUrl(userLinkDto);
	}
	
	@PutMapping
	public UserLinkDto updateURL(@Valid @RequestBody UserLinkDto userLinkDto) {
		
		try {
			return service.updateUrl(userLinkDto);
		} catch (DataIntegrityViolationException e) {
			throw new UrlDetailAlreadyExistException();
		}
	}
	
	@DeleteMapping("{id}")
	public void deleteURL(@PathVariable("id") Long id) {
		service.deleteUrl(id);
	}
}
