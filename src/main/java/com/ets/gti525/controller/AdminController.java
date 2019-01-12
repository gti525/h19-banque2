package com.ets.gti525.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	@GetMapping("/admin")
	public ModelAndView getMainPage() {
		
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		
		return new ModelAndView("admin");
	}
}