package com.ets.gti525.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@GetMapping("/user")
	public ModelAndView getMainPage() {
		return new ModelAndView("user");
	}
	
}
