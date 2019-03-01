package com.ets.gti525.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.request.CreateUserRequest;
import com.ets.gti525.domain.request.ResetPasswordRequest;
import com.ets.gti525.domain.response.CreateUserResponse;
import com.ets.gti525.domain.response.ResetPasswordResponse;
import com.ets.gti525.domain.response.SearchUsersResponse;
import com.ets.gti525.security.SecurityConfig;
import com.ets.gti525.service.UserService;

/**
 * Description : REST controller related to users of the application. 
 * This controller contains the operation to create a new user, for example.
 * 					
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 19-01-2019
 */
@RestController
@RequestMapping(value = "api/v1")
public class UserController {
	
	private final UserService userService;
	
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	@PatchMapping("/user/reset")
	public ResponseEntity<ResetPasswordResponse> resetPassword (@RequestBody ResetPasswordRequest request){
		ResetPasswordResponse response = userService.resetPassword(request);
	return ResponseEntity.status(response.getStatus()).body(response);
	
	}
	
	@PostMapping("/user")
	@PreAuthorize(SecurityConfig.ADMIN_CHECK)
	public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
		CreateUserResponse response = userService.createUser(request);
		return ResponseEntity.status(response.getStatus()).body(response);
	}	
	
	@GetMapping("/user")
	@PreAuthorize(SecurityConfig.ADMIN_CHECK)
	public ResponseEntity<SearchUsersResponse> searchUsers(@RequestParam(required=false) String keyword){
		SearchUsersResponse response = userService.searchUsers(keyword);
		return ResponseEntity.status(response.getStatus()).body(response);	
	}
}
