package com.ets.gti525.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.entity.Users;
import com.ets.gti525.domain.request.CreateUserRequest;
import com.ets.gti525.domain.response.CreateUserResponse;
import com.ets.gti525.service.UserService;

@RestController
public class UserController {
	
	private final UserService userService;
	
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/createUser")
	public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest request) {
		CreateUserResponse response = userService.createUser(request);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@PostMapping("/getUsers")
	public List<Users> getUsers() {
		return userService.getUsers();
	}
}
