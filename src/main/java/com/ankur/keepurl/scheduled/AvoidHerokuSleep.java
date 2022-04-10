package com.ankur.keepurl.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ankur.keepurl.app.util.URLUtility;

@Service
@ConditionalOnProperty(name="HEROKU_KEEPURL")
public class AvoidHerokuSleep {
	
	@Autowired
	private Environment env;
	
	/**
	 * This method will try access application every 20 minute
	 * in order to avoid application sleep enforced by HEROKU free plan.
	 */
	@Scheduled(fixedDelay = 1200000)
	public void accessApplication() {
		URLUtility.fetchTitle(env.getProperty("HEROKU_KEEPURL"));
	}
}
