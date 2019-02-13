package com.ets.gti525.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * Description : Configuration class related to sessions.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 28-01-2019
 */
@Configuration
public class SecurityBeans {

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPubliser() {
		return new HttpSessionEventPublisher();
	}
}