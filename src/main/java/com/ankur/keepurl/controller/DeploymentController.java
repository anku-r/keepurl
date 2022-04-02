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
public class DeploymentController {

	private static Logger logger = LoggerFactory.getLogger(DeploymentController.class);
	
	private static final String BRANCH_KEY = "ref";

	@Autowired
	private Environment env;

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deploy(@RequestBody LinkedHashMap<String, Object> payload) throws IOException {
		logger.info("Github Webhook Invoked");
		if (!payload.containsKey(BRANCH_KEY)) {
			logger.error("Property 'refs' missing");
			throw new KeepUrlServiceException("Property 'refs' missing. Cannot determine branch");
		}
		if (payload.get(BRANCH_KEY).toString().contains("/main")) {
			logger.info("Calling Deployment Script");
			Runtime.getRuntime().exec(env.getProperty("devop.dir") + "/deploy.sh");
			return "Deployment Triggered";
		}
		logger.info("Skipping Deployment as non main branch pushed");
		return "Deployment Skipped";
	}
}
