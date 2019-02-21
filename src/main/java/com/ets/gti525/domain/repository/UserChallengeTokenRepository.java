package com.ets.gti525.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ets.gti525.domain.entity.UserChallengeToken;

public interface UserChallengeTokenRepository extends JpaRepository<UserChallengeToken, Long> {

	public List<UserChallengeToken> findAllByUsernameIgnoreCase(String username);
	
	public UserChallengeToken findByToken(String token);
	
	public void deleteByToken(String token);
}
