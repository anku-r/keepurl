package com.ankur.keepurl.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ankur.keepurl.manager.api.TrashService;

@Service
public class TrashCleanup {
	
	@Autowired
	private TrashService service;

	/**
	 * This method will cleanup trash 
	 * and is executed every day at 00:00:00
	 */
	@Scheduled(cron = "0 0 0 * * *")
	public void execute() {	
		service.trashCleanup();
	}
}
