package com.ets.gti525.security;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfigurer implements WebMvcConfigurer {

	private static final String [] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/public/"};
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(CLASSPATH_RESOURCE_LOCATIONS);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/LoginAdmin").setViewName("forward:/");
	}
}
