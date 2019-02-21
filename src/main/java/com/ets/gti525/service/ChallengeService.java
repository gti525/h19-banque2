package com.ets.gti525.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ets.gti525.domain.entity.Challenge;
import com.ets.gti525.domain.entity.UserChallengeToken;
import com.ets.gti525.domain.repository.ChallengeRepository;
import com.ets.gti525.domain.repository.UserChallengeTokenRepository;
import com.ets.gti525.domain.request.ChallengeValidationRequest;
import com.ets.gti525.domain.response.ChallengeResponse;
import com.ets.gti525.domain.response.ChallengeValidationResponse;

@Service
public class ChallengeService {
	
	private final ChallengeRepository challengeRepo;
	private final UserChallengeTokenRepository challengeTokenRepo;
	
	public ChallengeService(final ChallengeRepository challengeRepo,
			final UserChallengeTokenRepository challengeTokenRepo) {
		this.challengeRepo = challengeRepo;
		this.challengeTokenRepo = challengeTokenRepo;
	}

	public ChallengeResponse getChallenge(String username) {
		
		List<Challenge> challenges = challengeRepo.findAllByUsernameIgnoreCase(username);
		
		if (challenges.size() < 1)
			return new ChallengeResponse(HttpStatus.BAD_REQUEST, "No challenge found.");
		
		Collections.shuffle(challenges);
		Challenge challenge = challenges.get(0);
		
		deleteUserChallengeTokenIfExists(challenge.getUsername());
		
		UserChallengeToken uct = new UserChallengeToken(challenge.getUsername(), 
				challenge.getId(), null, null);
		
		challengeTokenRepo.save(uct);
		
		return new ChallengeResponse(HttpStatus.OK, challenges.get(0).getQuestion());
	}
	
	public ChallengeValidationResponse validateResponse(String username, ChallengeValidationRequest request) {
		
		List<UserChallengeToken> ucts = challengeTokenRepo.findAllByUsernameIgnoreCase(username);
		String response = request.getUserResponse();
		
		if (ucts.size() != 1)
			return new ChallengeValidationResponse(HttpStatus.FORBIDDEN);
		
		UserChallengeToken uct = ucts.get(0);
		Challenge challenge = challengeRepo.findById(uct.getChallengeId());
		
		if (challenge == null || response == null || uct.getToken() != null)
			return new ChallengeValidationResponse(HttpStatus.FORBIDDEN);
		
		if (challenge.getResponse().toUpperCase().equals(response.toUpperCase()))
			return new ChallengeValidationResponse(HttpStatus.OK, setTokenForUser(uct));
		
		return new ChallengeValidationResponse(HttpStatus.FORBIDDEN);
	}
	
	public UserChallengeToken getUsernameChallengeByToken(String token) {
		return challengeTokenRepo.findByToken(token);
	}
	
	private void deleteUserChallengeTokenIfExists(String username) {
		List<UserChallengeToken> ucts = challengeTokenRepo.findAllByUsernameIgnoreCase(username);
		challengeTokenRepo.deleteAll(ucts);
	}
	
	private String setTokenForUser(UserChallengeToken uct) {
		String token = UUID.randomUUID().toString();
		uct.setToken(token);
		uct.setTimestamp(new Date());
		challengeTokenRepo.save(uct);
		return token;
	}
}
