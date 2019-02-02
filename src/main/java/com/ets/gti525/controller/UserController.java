package com.ets.gti525.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.entity.User;
import com.ets.gti525.domain.request.CreateUserRequest;
import com.ets.gti525.domain.response.CreateUserResponse;
import com.ets.gti525.domain.response.SearchUsersResponse;
import com.ets.gti525.service.UserService;

@RestController
@RequestMapping(value = "api/v1")
public class UserController {
	
	private final UserService userService;
	
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/user")
	public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
		CreateUserResponse response = userService.createUser(request);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	
	@GetMapping("/users/{keyword}")
	public ResponseEntity<SearchUsersResponse> searchUsers(@PathVariable String keyword){
		SearchUsersResponse response = userService.searchUsers(keyword);
		return ResponseEntity.status(response.getStatus()).body(response);	
	}
}
