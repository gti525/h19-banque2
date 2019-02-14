package com.ets.gti525.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	@GetMapping(ERROR_PATH)
	public String handleError(HttpServletRequest request) {
		return "redirect:/?redirect=" + 
				request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
	}
	
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}
