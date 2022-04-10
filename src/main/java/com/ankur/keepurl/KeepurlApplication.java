package com.ankur.keepurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KeepurlApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeepurlApplication.class, args);
	}
}
