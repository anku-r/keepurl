package com.ankur.keepurl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/deploy")
public class DeployerController {
	
	private static Logger logger = LoggerFactory.getLogger(DeployerController.class);

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deploy(@RequestBody String payload) {
		logger.info("Deployment OK: " + payload);
	}
}
