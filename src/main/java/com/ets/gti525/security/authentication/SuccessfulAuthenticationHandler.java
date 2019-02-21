package com.ets.gti525.security.authentication;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ets.gti525.domain.constant.Role;

/**
 * Description : Class containing actions to do after a successful authentication.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 28-01-2019
 */
@Component
public class SuccessfulAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final String DEFAULT_TARGET_PATH = "/";
	private static final String ADMIN_TARGET_PATH = "/admin";
	private static final String USER_TARGET_PATH = "/user";
	
	private static final int SESSION_TIMEOUT = 300; // in seconds
	
	private final SessionRegistry sessionRegistry;
	
	public SuccessfulAuthenticationHandler (final SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		System.out.println("Authentication success for user : " + authentication.getName());
		
		String targetUrl = getTargetUrl(authentication.getAuthorities());
		
		setAlwaysUseDefaultTargetUrl(false);
		setDefaultTargetUrl(targetUrl);
		
		super.onAuthenticationSuccess(request, response, authentication);
		
		HttpSession session = request.getSession(false);
		session.setMaxInactiveInterval(SESSION_TIMEOUT);

		expireOlderSessions(authentication.getPrincipal());
	}
	
	private String getTargetUrl(Collection<? extends GrantedAuthority> authorities) {
		
		String targetUrl = DEFAULT_TARGET_PATH;
		
		Iterator<? extends GrantedAuthority> roleIter = authorities.iterator();
		
		while (roleIter.hasNext()) {
			GrantedAuthority ga = roleIter.next();
			
			if (ga.getAuthority().equals(Role.ADMIN.toString())) {
				targetUrl = ADMIN_TARGET_PATH;
				break;
			} else if (ga.getAuthority().equals(Role.USER.toString())) {
				targetUrl = USER_TARGET_PATH;
				break;
			}
		}
		
		return targetUrl;
	}
	
	private void expireOlderSessions(Object principal) {
		
		List<Object> principals = sessionRegistry.getAllPrincipals();
		Map<String, SessionInformation> userSessionMap = new HashMap<>();
		
		Iterator<Object> iter = principals.iterator();
		
		while (iter.hasNext()) {
			String currentPrincipal = iter.next().toString();
			SessionInformation sessionInformation = sessionRegistry.getAllSessions(currentPrincipal, false).get(0);
			
			if (userSessionMap.containsKey(currentPrincipal)) {
				userSessionMap.get(currentPrincipal).expireNow();
			} else {
				userSessionMap.put(currentPrincipal, sessionInformation);
			}
		}
	}
}
