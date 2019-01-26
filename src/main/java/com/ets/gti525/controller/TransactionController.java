package com.ets.gti525.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.request.CreditCardPaymentRequest;
import com.ets.gti525.domain.response.CreditCardTransactionsResponse;
import com.ets.gti525.domain.response.DebitCardTransactionsResponse;
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
	
	@GetMapping(value = "transaction/creditCard/{nbr}")
	public CreditCardTransactionsResponse getCreditCardTransactions(@PathVariable long nbr) {
		return transactionService.getCreditCardTransactions(nbr);
	}
	
	@GetMapping(value = "transaction/debitCard/{nbr}")
	public DebitCardTransactionsResponse getDebitCardTransactions(@PathVariable long nbr) {
		return transactionService.getDebitCardTransactions(nbr);
	}
	
	@PostMapping(value = "transaction/creditCardPayment")
	public ResponseEntity<TransactionReply> payForCreditCard(@RequestBody CreditCardPaymentRequest request) {	
		return ResponseEntity.ok(transactionService.processCreditCardPayment(request));
	}
}
