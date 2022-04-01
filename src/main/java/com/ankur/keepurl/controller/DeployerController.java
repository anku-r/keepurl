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

import com.ankur.keepurl.app.exception.KeepUrlServiceException;

@RestController
@RequestMapping("api/deploy")
public class DeployerController {
	
	private static Logger logger = LoggerFactory.getLogger(DeployerController.class);
	
	@Autowired
	private Environment env;

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deploy(@RequestBody LinkedHashMap<String, Object> payload) throws IOException {
		if (payload.containsKey("refs")) {
			logger.error("Github Webhook: Property 'refs' missing");
			throw new KeepUrlServiceException("Property 'refs' missing. Cannot determine branch");
		}
		if (payload.get("refs").toString().contains("/main")) {
			logger.info("Github Webhook: Calling Deployment Script");
			Runtime.getRuntime().exec(env.getProperty("devop.dir")+"/deploy.sh");
			return "Deployment Triggered";
		}
		logger.info("Github Webhook: Skipping Deployment as non main branch pushed");
		return "Deployment Skipped";
	}
}
