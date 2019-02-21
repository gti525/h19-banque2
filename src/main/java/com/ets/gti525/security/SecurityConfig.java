package com.ets.gti525.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Description : Configuration class related to SpringSecurity.
 * Contains configuration about authorizations of requests.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 11-01-2019
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final CustomUserDetailsService customUserDetailsService;
	private final AuthenticationSuccessHandler successfulAuthenticationHandler;
	private final SessionRegistry sessionRegistry;
	
	public SecurityConfig(final CustomUserDetailsService customUserDetailsService,
			final SuccessfulAuthenticationHandler successfulAuthenticationHandler,
			final SessionRegistry sessionRegistry) {
		this.customUserDetailsService = customUserDetailsService;
		this.successfulAuthenticationHandler = successfulAuthenticationHandler;
		this.sessionRegistry = sessionRegistry;
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/user").hasAnyAuthority("USER", "ADMIN")
			.antMatchers("/admin").hasAnyAuthority("ADMIN")
			.antMatchers("/api/**").permitAll()		// Temporary.
			.antMatchers("/poc-lp/*").hasAnyAuthority("USER", "ADMIN")  // Tests LP
			.antMatchers("/api/v1/transaction/**").hasAnyAuthority("USER", "ADMIN")
			.antMatchers("/api/v1/paymentGateway/**").permitAll() // Verified with header auth anyway
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/").permitAll()
			.and().formLogin().loginPage("/LoginAdmin").loginProcessingUrl("/login")
			.successHandler(successfulAuthenticationHandler)
			.and().cors().disable()
			.csrf().disable()
			.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry).expiredUrl("/LoginAdmin");
		
		// H2-console
		http.headers().frameOptions().disable();
		
	}
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}