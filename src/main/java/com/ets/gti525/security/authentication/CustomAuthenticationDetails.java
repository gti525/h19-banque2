package com.ets.gti525.security.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomAuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = 1L;
	
	private String token;

	public CustomAuthenticationDetails(HttpServletRequest request) {
		super(request);
		this.token = request.getParameter("token");
	}
	
	public String getToken() {
		return token;
	}

}
