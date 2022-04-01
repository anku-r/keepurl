package com.ankur.keepurl.controller;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/deploy")
public class DeployerController {
	
	private static Logger logger = LoggerFactory.getLogger(DeployerController.class);
	
	@Autowired
	private Environment env;

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deploy(@RequestBody LinkedHashMap<String, Object> payload) throws IOException {
		if (payload.get("ref").equals("refs/head/main")) {
			logger.info("Calling Deployment Script");
			Runtime.getRuntime().exec(env.getProperty("devop.dir")+"/deploy.sh");
			return "Deployment Triggered";
		}
		return "Deployment Skipped";
	}
}
