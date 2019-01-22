package com.ets.gti525.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.request.TransactionRequest;
import com.ets.gti525.service.TransactionService;



@RestController
public class PaymentGatewayController {

	private final TransactionService transactionService;

	/*
	 * L'injection se fait automatiquement par Spring (Puisque annoté @Controller)
	 * Ce n'est pas une bonne pratique de toujours utiliser @Autowired
	 */
	public PaymentGatewayController(final TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("/api/paymentGateway")
	public ResponseEntity<?> processTransaction(@RequestHeader(value="X-API-KEY") String apiKey, @RequestBody TransactionRequest request) {
	
		if(!transactionService.verifyAPIKey(apiKey)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		return ResponseEntity.ok(transactionService.processTransaction(apiKey, request));

	}


}
