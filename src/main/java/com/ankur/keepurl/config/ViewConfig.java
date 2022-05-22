package com.ankur.keepurl.config;

import java.util.function.BiConsumer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
	
	BiConsumer<String, String> viewController = (controller, view)
			-> registry.addViewController(controller).setViewName(view);
	
	viewController.accept("/trash", "trash.html");
	viewController.accept("/login", "login.html");
    }
}
