package com.ets.gti525.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ets.gti525.domain.constant.Role;
import com.ets.gti525.domain.entity.Users;
import com.ets.gti525.domain.repository.UsersRepository;
import com.ets.gti525.domain.request.CreateUserRequest;
import com.ets.gti525.domain.response.CreateUserResponse;

@Service
public class UserService {

	private final UsersRepository usersRepository;
	
	public UserService(final UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public List<Users> getUsers() {
		return usersRepository.findAll();
	}
	
	public CreateUserResponse createUser(CreateUserRequest request) {
		
		String username = generateAccountNumber();
		String password = generatePassword();
		String message = "Account successfully created.";
		
		String encodedPassword = encodePassword(password);

		Users user = new Users(username, encodedPassword, Role.USER, 1);
		
		usersRepository.save(user);
		
		return new CreateUserResponse(HttpStatus.OK, message, username, password);
	}
	
	private String generateAccountNumber() {
		return "4540123456780000";
	}
	
	private String generatePassword() {
		return "qwerty";
	}
	
	private String encodePassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
}
