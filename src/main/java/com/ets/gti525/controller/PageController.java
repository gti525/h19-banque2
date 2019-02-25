package com.ets.gti525.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController implements ErrorController {

	private static final String ERROR_PATH = "/error";
	
	@GetMapping("/DashboardAdmin")
	public String handleAdminLoginRedirect(HttpServletRequest request) {
		return "forward:/";
	}
	
	@GetMapping("/DashboardClient")
	public String handleClientLoginRedirect(HttpServletRequest request) {
		return "forward:/";
	}

	@GetMapping(ERROR_PATH)
	public String handleError(HttpServletRequest request) {
		return "forward:/";
	}
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}
