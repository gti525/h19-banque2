package com.ets.gti525.service;

import org.springframework.stereotype.Service;

import com.ets.gti525.request.TransactionRequest;
import com.ets.gti525.response.TransactionReply;

@Service
public class TransactionService {
	
	public static final String BANK_2_ID = "9bb9426e-f176-4a76-9be5-68709325e43c";

	public TransactionReply processTransaction(TransactionRequest request) {
		TransactionReply reply = new TransactionReply();
		
		if(!request.getBankId().equals(BANK_2_ID)) {
			reply.setResult(TransactionReply.DECLINED);
			return reply;
		}
		
		System.out.println("Bank ID: " + request.getBankId());
		System.out.println("Amount: " + request.getAmount());
		System.out.println("Merchant: " + request.getMerchant());
		System.out.println("AccountNumber: " + request.getAccount().getNumber());
		System.out.println("AccountMonthExp: " + request.getAccount().getMonthExp());
		System.out.println("AccountYearExp: " + request.getAccount().getYearExp());
		System.out.println("AuthHash: " + request.getAccount().getAuthHash());


		reply.setResult(TransactionReply.ACCEPTED);
		return reply;
	}

}
