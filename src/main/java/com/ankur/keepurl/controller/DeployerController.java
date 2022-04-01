package com.ankur.keepurl.controller;

import java.io.IOException;

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
	public String deploy(@RequestBody String payload) throws IOException {
		logger.info("Calling Deployment Script");
		System.out.println("PWD : " + System.getProperty("java.class.path"));
		Runtime.getRuntime().exec("/home/keepurl/devop/deploy.sh");
		return "Deployment Triggered";
	}
}
