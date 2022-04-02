package com.ankur.keepurl.app.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
//@EnableScheduling
public class AvoidHerokuSleep {
	
	@Value("${HEROKU_KEEPURL}")
	private String herokuKeepURL;
	
	/**
	 * This method will try access application every 20 minute
	 * in order to avoid sleep enforced by HEROKU free plan.
	 */
	@Scheduled(fixedDelay = 1200000)
	public void accessApplication() {
		URLUtility.fetchTitle(herokuKeepURL);
	}
}
