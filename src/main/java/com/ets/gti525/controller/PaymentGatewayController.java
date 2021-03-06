package com.ets.gti525.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.request.PreAuthCCTransactionRequest;
import com.ets.gti525.domain.request.ProcessCCTransactionRequest;
import com.ets.gti525.domain.response.AbstractResponse;
import com.ets.gti525.service.TransactionService;

/**
 * Description : REST controller for payments.
 * 					
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 16-01-2019
 */
@RestController
@RequestMapping(value = "/api/v1")
public class PaymentGatewayController {

	private final TransactionService transactionService;

	/*
	 * L'injection se fait automatiquement par Spring (Puisque annoté @Controller)
	 * Ce n'est pas une bonne pratique de toujours utiliser @Autowired
	 */
	public PaymentGatewayController(final TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("/paymentGateway/preAuth")
	public ResponseEntity<AbstractResponse> preAuthCCTransaction(@RequestHeader(value="X-API-KEY") String apiKey,
			@RequestBody PreAuthCCTransactionRequest request) {
		
		if(!transactionService.verifyAPIKey(apiKey)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		AbstractResponse response = transactionService.preAuthCCTransaction(apiKey, request);
		return ResponseEntity.status(response.getStatus()).body(response);
		
	}
	
	@PostMapping("/paymentGateway/process")
	public ResponseEntity<AbstractResponse> processCCTransaction(@RequestHeader(value="X-API-KEY") String apiKey,
			@RequestBody ProcessCCTransactionRequest request) {
		
		if(!transactionService.verifyAPIKey(apiKey)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		AbstractResponse response = transactionService.processCCTransaction(request);
		return ResponseEntity.status(response.getStatus()).body(response);
		
	}
	

}
