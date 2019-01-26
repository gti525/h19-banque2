package com.ets.gti525.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.response.CreditCardInfoResponse;
import com.ets.gti525.domain.response.DebitCardInfoResponse;
import com.ets.gti525.service.AccountService;

@RestController
@RequestMapping(value = "/api/v1")
public class AccountController {
	
	private final AccountService accountService;
	
	public AccountController(final AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping(path = "/account/creditCard/{nbr}")
	public CreditCardInfoResponse getCreditCardInfo(@PathVariable long nbr) {
		return accountService.getCreditCardInfo(nbr);
	}
	
	@GetMapping(path = "/account/debitCard/{nbr}")
	public DebitCardInfoResponse getDebitCardInfo(@PathVariable long nbr) {
		return accountService.getDebitCardInfo(nbr);
	}
}