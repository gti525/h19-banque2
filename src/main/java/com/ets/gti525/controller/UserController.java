package com.ets.gti525.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@PostMapping("createUser")
	public void createUser() {
		System.out.println("creating user");
	}
}
