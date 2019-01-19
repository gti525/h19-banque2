package com.ets.gti525.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ets.gti525.domain.request.TransactionRequest;
import com.ets.gti525.domain.response.TransactionReply;
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
	public @ResponseBody TransactionReply processTransaction(@RequestBody TransactionRequest request) {
	
		return transactionService.processTransaction(request);

	}


}
