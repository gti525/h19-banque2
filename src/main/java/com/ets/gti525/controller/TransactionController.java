package com.ets.gti525.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.request.CreditCardPaymentRequest;
import com.ets.gti525.domain.response.TransactionReply;
import com.ets.gti525.service.TransactionService;

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
	
	@PostMapping(value = "/creditCardPayment")
	public ResponseEntity<TransactionReply> payForCreditCard(@RequestBody CreditCardPaymentRequest request) {	
		return ResponseEntity.ok(transactionService.processCreditCardPayment(request));
	}
}
