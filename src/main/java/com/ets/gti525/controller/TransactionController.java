package com.ets.gti525.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.request.CreditCardPaymentRequest;
import com.ets.gti525.domain.request.BankTransferRequest;
import com.ets.gti525.domain.response.AbstractResponse;
import com.ets.gti525.security.SecurityConfig;
import com.ets.gti525.service.TransactionService;

/**
 * Description : REST controller related to transactions (get transactions of a credit card for example).
 * 					
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 16-01-2019
 */
@RestController
@RequestMapping(value = "/api/v1")
public class TransactionController {
	
	private final TransactionService transactionService;

	/*
	 * L'injection se fait automatiquement par Spring (Puisque annoté @Controller)
	 * Ce n'est pas une bonne pratique de toujours utiliser @Autowired
	 */
	public TransactionController(final TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	

	
	@GetMapping(value = "creditCard/{nbr}/transaction")
	@PreAuthorize(SecurityConfig.ADMIN_CHECK)
	public ResponseEntity<AbstractResponse> getCreditCardTransactions(@PathVariable long nbr) {
		AbstractResponse response = transactionService.getCreditCardTransactions(nbr);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@GetMapping(value = "creditCard/transaction")
	@PreAuthorize(SecurityConfig.AUTHENTICATED_CHECK)
	public ResponseEntity<AbstractResponse> getMyCreditCardTransactions() {
		AbstractResponse response = transactionService.getMyCreditCardTransactions();
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@GetMapping(value = "debitCard/{nbr}/transaction")
	@PreAuthorize(SecurityConfig.ADMIN_CHECK)
	public ResponseEntity<AbstractResponse> getDebitCardTransactions(@PathVariable long nbr) {
		AbstractResponse response = transactionService.getDebitCardTransactions(nbr);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@GetMapping(value = "debitCard/transaction")
	@PreAuthorize(SecurityConfig.AUTHENTICATED_CHECK)
	public ResponseEntity<AbstractResponse> getMyDebitCardTransactions() {
		AbstractResponse response = transactionService.getMyDebitCardTransactions();
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@PostMapping(value = "transaction/creditCardPayment")
	@PreAuthorize(SecurityConfig.AUTHENTICATED_CHECK)
	public ResponseEntity<AbstractResponse> payForCreditCard(@RequestBody CreditCardPaymentRequest request) {	
		AbstractResponse response = transactionService.processCreditCardPayment(request);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@PostMapping(value = "transaction/bankTransfer")
	public ResponseEntity<AbstractResponse> bankTransfer(@RequestBody BankTransferRequest request, @RequestHeader(value="X-API-KEY", required=false) String apiKey) {	
		AbstractResponse response = transactionService.processBankTransfer(request, apiKey);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	

	
}
