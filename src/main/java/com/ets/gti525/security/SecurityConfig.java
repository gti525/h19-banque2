package com.ets.gti525.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomUserDetailsService customUserDetailsService;
	
	public SecurityConfig(final CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
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
			//.antMatchers("/api/**").permitAll()		// Temporary.
			.antMatchers("/api/v1/transaction/**").hasAnyAuthority("USER", "ADMIN")
			.antMatchers("/api/v1/paymentGateway/**").permitAll() // Verified with header auth anyway
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/").permitAll()
			.and().formLogin()
			.and().csrf().disable();
		
		// H2-console
		http.headers().frameOptions().disable();
		
	}
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}