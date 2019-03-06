package com.ets.gti525.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ets.gti525.security.SecurityConfig;

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
	
	@PreAuthorize(SecurityConfig.ADMIN_CHECK)
	@GetMapping("/api/v1/admin/ping")
	public ResponseEntity<String> pingAdmin(){
		return ResponseEntity.ok("You are logged as an admin my friend");
	}
	
	// For a lack of a better place to put this
	@PreAuthorize(SecurityConfig.USER_CHECK)
	@GetMapping("/api/v1/client/ping")
	public ResponseEntity<String> pingClient(){
		return ResponseEntity.ok("You are logged as a user my friend");
	}
}