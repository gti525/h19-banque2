package com.ets.gti525.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.request.BankTransferRequest;
import com.ets.gti525.domain.response.AbstractResponse;
import com.ets.gti525.service.TransactionService;

/**
 * Description : REST controller for operations between different banks 
 * (transfer funds between two banks for example).
 * 					
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 09-02-2019
 */
@RestController
@RequestMapping(value = "/api/v1")
public class BankToBankController {
	
	private final TransactionService transactionService;

	/*
	 * L'injection se fait automatiquement par Spring (Puisque annoté @Controller)
	 * Ce n'est pas une bonne pratique de toujours utiliser @Autowired
	 */
	public BankToBankController(final TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	@PostMapping("banktobank/transfer")
	public ResponseEntity<AbstractResponse> bankToBankTransfer(@RequestHeader(value="X-API-KEY") String apiKey,
			@RequestBody BankTransferRequest request){
		AbstractResponse response = transactionService.receiveBankToBankTransfer(apiKey, request);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

}
