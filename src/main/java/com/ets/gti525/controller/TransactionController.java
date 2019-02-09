package com.ets.gti525.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.request.CreditCardPaymentRequest;
import com.ets.gti525.domain.request.BankTransferRequest;
import com.ets.gti525.domain.response.AbstractResponse;
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
	

	
	@GetMapping(value = "transaction/creditCard/{nbr}")
	public ResponseEntity<AbstractResponse> getCreditCardTransactions(@PathVariable long nbr) {
		AbstractResponse response = transactionService.getCreditCardTransactions(nbr);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@GetMapping(value = "transaction/debitCard/{nbr}")
	public ResponseEntity<AbstractResponse> getDebitCardTransactions(@PathVariable long nbr) {
		AbstractResponse response = transactionService.getDebitCardTransactions(nbr);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@PostMapping(value = "transaction/creditCardPayment")
	public ResponseEntity<AbstractResponse> payForCreditCard(@RequestBody CreditCardPaymentRequest request) {	
		AbstractResponse response = transactionService.processCreditCardPayment(request);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@PostMapping(value = "transaction/intraBankTransfer")
	public ResponseEntity<AbstractResponse> intraBankTransfer(@RequestBody BankTransferRequest request) {	
		AbstractResponse response = transactionService.processBankTransfer(request);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	

	
}
