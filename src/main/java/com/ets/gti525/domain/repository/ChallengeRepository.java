package com.ets.gti525.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ets.gti525.domain.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

	public List<Challenge> findAllByUsernameIgnoreCase(String username);
	
	public Challenge findById(int id);
}
