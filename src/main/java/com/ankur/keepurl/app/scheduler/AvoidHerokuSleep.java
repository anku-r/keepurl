package com.ankur.keepurl.app.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ankur.keepurl.app.util.URLUtility;

@Service
@EnableScheduling
public class AvoidHerokuSleep {
	
	@Autowired
	private Environment env;
	
	/**
	 * This method will try access application every 20 minute
	 * in order to avoid sleep enforced by HEROKU free plan.
	 */
	@Scheduled(fixedDelay = 1200000)
	public void accessApplication() {
		URLUtility.fetchTitle(env.getProperty("HEROKU_KEEPURL"));
	}
}
