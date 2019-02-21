package com.ets.gti525.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ets.gti525.security.authentication.CustomAuthenticationDetailsSource;
import com.ets.gti525.security.authentication.SuccessfulAuthenticationHandler;
import com.ets.gti525.security.authentication.TokenAuthenticationProvider;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final AuthenticationSuccessHandler successfulAuthenticationHandler;
	private final SessionRegistry sessionRegistry;
	private final CustomAuthenticationDetailsSource customAuthenticationDetailsSource;
	private final TokenAuthenticationProvider tokenAuthProvider;
	
	public SecurityConfig(final SuccessfulAuthenticationHandler successfulAuthenticationHandler,
			final SessionRegistry sessionRegistry,
			final CustomAuthenticationDetailsSource customAuthenticationDetailsSource,
			final TokenAuthenticationProvider tokenAuthProvider) {
		this.successfulAuthenticationHandler = successfulAuthenticationHandler;
		this.sessionRegistry = sessionRegistry;
		this.customAuthenticationDetailsSource = customAuthenticationDetailsSource;
		this.tokenAuthProvider = tokenAuthProvider;
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(tokenAuthProvider);
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
			.and().formLogin()
			.authenticationDetailsSource(customAuthenticationDetailsSource)
			.loginPage("/LoginAdmin").loginProcessingUrl("/login")
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