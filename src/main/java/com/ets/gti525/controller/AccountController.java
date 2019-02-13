package com.ets.gti525.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.response.AbstractResponse;
import com.ets.gti525.service.AccountService;

/**
 * Description : REST controller for operations related to client accounts
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 25-01-2019
 */
@RestController
@RequestMapping(value = "/api/v1")
public class AccountController {
	
	private final AccountService accountService;
	
	public AccountController(final AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping(path = "/account/creditCard/{nbr}")
	public ResponseEntity<AbstractResponse> getCreditCardInfo(@PathVariable long nbr) {
		AbstractResponse response = accountService.getCreditCardInfo(nbr);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@GetMapping(path = "/account/debitCard/{nbr}")
	public ResponseEntity<AbstractResponse> getDebitCardInfo(@PathVariable long nbr) {
		AbstractResponse response = accountService.getDebitCardInfo(nbr);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
}