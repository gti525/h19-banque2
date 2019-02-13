package com.ets.gti525.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ets.gti525.domain.entity.User;
import com.ets.gti525.domain.repository.UsersRepository;

/**
 * Description : Service containing operations related to authorization in the application.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 19-01-2019
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UsersRepository usersRepository;
	
	public CustomUserDetailsService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (username == null)
			throw new UsernameNotFoundException("Username cannot be null");
		
		List<User> usersList = usersRepository.findByUsername(username.toUpperCase());
		
		if (usersList.size() != 1) {
			throw new UsernameNotFoundException(username);
		} else {
			return usersList.get(0);
		}
	}
}