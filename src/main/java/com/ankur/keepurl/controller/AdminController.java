package com.ankur.keepurl.controller;

import java.io.IOException;
import java.util.Map;

import com.ankur.keepurl.interceptor.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ankur.keepurl.exception.KeepUrlServiceException;

import lombok.extern.slf4j.Slf4j;

import static com.ankur.keepurl.utility.AppConstants.*;

@Slf4j
@RestController
@RequestMapping("api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

	@Autowired
	private CacheManager cacheManager;

	@PostMapping("deploy")
    public String deploy(@RequestBody Map<String, Object> payload) throws IOException {
	log.info("Github Webhook Invoked");
	if (!payload.containsKey(BRANCH_KEY)) {
	    log.error("Property {} missing", BRANCH_KEY);
	    throw new KeepUrlServiceException("Property " + BRANCH_KEY + " missing. Cannot determine branch");
	}
	if (payload.get(BRANCH_KEY).toString().contains(BRANCH)) {
	    log.info("Calling Deployment Script: {}", DEPLOYMENT_SCRIPT);
	    Runtime.getRuntime().exec(DEPLOYMENT_SCRIPT);
	    return "Deployment Triggered";
	}
	log.info("Skipping Deployment as non main branch pushed");
	return "Deployment Skipped";
    }

	@GetMapping("clearCache/{entity}")
	public void clearCache(@PathVariable("entity") String entity) {
		cacheManager.clearCacheForEntity(entity);
	}

}
