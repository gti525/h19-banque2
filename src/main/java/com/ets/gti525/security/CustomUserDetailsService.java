package com.ets.gti525.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ets.gti525.domain.entity.User;
import com.ets.gti525.domain.entity.UserChallengeToken;
import com.ets.gti525.domain.repository.UsersRepository;
import com.ets.gti525.service.ChallengeService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UsersRepository usersRepository;
	private final ChallengeService challengeService;
	
	public CustomUserDetailsService(UsersRepository usersRepository,
			final ChallengeService challengeService) {
		this.usersRepository = usersRepository;
		this.challengeService = challengeService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (username == null)
			throw new UsernameNotFoundException("Username cannot be null.");
		
		List<User> usersList = usersRepository.findByUsername(username.toUpperCase());
		
		if (usersList.size() != 1) {
			throw new UsernameNotFoundException(username);
		} else {
			return usersList.get(0);
		}
	}
	
	public UserDetails loadByToken(String token) throws UsernameNotFoundException {
		
		if (token == null)
			throw new UsernameNotFoundException("Token cannot be null.");
		
		UserChallengeToken uct = challengeService.getUsernameChallengeByToken(token);
		
		if (uct == null)
			throw new UsernameNotFoundException("Invalid token.");
		
		return loadUserByUsername(uct.getUsername());
		
	}

}
