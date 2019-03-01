package com.ets.gti525.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description : Controller for operations related to administrative tasks.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 11-01-2019
 */
@Controller
public class AdminController {

	@GetMapping("/admin")
	public ModelAndView getMainPage() {
		
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		
		return new ModelAndView("admin");
	}
}