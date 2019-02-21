package com.ets.gti525.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.request.ChallengeValidationRequest;
import com.ets.gti525.domain.response.ChallengeResponse;
import com.ets.gti525.domain.response.ChallengeValidationResponse;
import com.ets.gti525.service.ChallengeService;

@RestController
@RequestMapping("/api/v1")
public class ChallengeController {
	
	private final ChallengeService challengeService;
	
	public ChallengeController(final ChallengeService challengeService) {
		this.challengeService = challengeService;
	}

	@GetMapping("/challenge/{username}")
	public ResponseEntity<ChallengeResponse> getChallenge(@PathVariable String username) {
		ChallengeResponse response = challengeService.getChallenge(username);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@PostMapping("/challenge/{username}/validate")
	public ResponseEntity<ChallengeValidationResponse> validateResponse(@PathVariable String username, @RequestBody ChallengeValidationRequest userResponse) {
		ChallengeValidationResponse response = challengeService.validateResponse(username, userResponse);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
}
