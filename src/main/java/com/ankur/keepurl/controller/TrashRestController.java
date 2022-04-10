package com.ankur.keepurl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankur.keepurl.manager.api.TrashService;
import com.ankur.keepurl.manager.model.TrashDTO;

@RestController
@RequestMapping("api/trash")
public class TrashRestController {

	@Autowired
	private TrashService service;
	
	@GetMapping
	public List<TrashDTO> getTrashLinks() {
		return service.getAllLinks();
	}
	
	@DeleteMapping("{id}")
	public void deleteTrashLink(@PathVariable("id") String id) {
		service.delete(id);
	}
	
	@DeleteMapping("restore/{id}")
	public void restoreLink(@PathVariable("id") String id) {
		service.restore(id);
	}
}
