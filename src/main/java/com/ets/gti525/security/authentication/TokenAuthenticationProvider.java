package com.ets.gti525.security.authentication;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ets.gti525.domain.constant.Role;
import com.ets.gti525.security.CustomUserDetailsService;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

	private final CustomUserDetailsService userDetailsService;
	
	public TokenAuthenticationProvider(final CustomUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		try {
			
			CustomAuthenticationDetails authDetails = (CustomAuthenticationDetails) authentication.getDetails();
			String token = authDetails.getToken();
			String username = authentication.getName();
			
			if (token != null) {
				UserDetails user = userDetailsService.loadByToken(token);
				
				if (isPasswordValid(authentication.getCredentials().toString(), user.getPassword())) {
					return new UsernamePasswordAuthenticationToken(user.getUsername(), 
							user.getPassword(), user.getAuthorities());
				}
				
			} else {
				UserDetails user = userDetailsService.loadUserByUsername(username);
				
				if (isPasswordValid(authentication.getCredentials().toString(), user.getPassword()) 
						&& isAdmin(user.getAuthorities())) {
					return new UsernamePasswordAuthenticationToken(user.getUsername(), 
							user.getPassword(), user.getAuthorities());
				}
			}
			
			throw new UsernameNotFoundException("Bad credentials.");
			
		} catch(Exception e) {
			throw new UsernameNotFoundException("Bad credentials.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}
	
	private boolean isAdmin(Collection<? extends GrantedAuthority> authorities) {
		
		Iterator<? extends GrantedAuthority> roleIter = authorities.iterator();
		
		while (roleIter.hasNext()) {
			GrantedAuthority ga = roleIter.next();
			
			if (ga.getAuthority().equals(Role.ADMIN.toString()))
				return true;
		}
		
		return false;
	}
	
	private boolean isPasswordValid(String rawPassword, String encodedPassword) {
		return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
	}

}
