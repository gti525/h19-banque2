package com.ets.gti525.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description : Controller for operations related to user tasks.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 11-01-2019
 */
@Controller
public class AppController {

	@GetMapping("/user")
	public ModelAndView getMainPage() {
		return new ModelAndView("user");
	}
	
}