package com.ets.gti525.service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.ets.gti525.domain.repository.PaymentBrokerRepository;
import com.ets.gti525.domain.request.TransactionRequest;
import com.ets.gti525.domain.response.TransactionReply;

@Service
public class TransactionService {

	private final PaymentBrokerRepository paymentBrokerRepository;

	public TransactionService(final PaymentBrokerRepository paymentBrokerRepository) {
		this.paymentBrokerRepository = paymentBrokerRepository;
	}

	public static final String BANK_2_ID = "9bb9426e-f176-4a76-9be5-68709325e43c";

	public TransactionReply processTransaction(String apiKey, TransactionRequest request) {
		TransactionReply reply = new TransactionReply();
		String secret = paymentBrokerRepository.findByApiKey(apiKey).getSecret();

		if(!request.getBankId().equals(BANK_2_ID)) {
			reply.setResult(TransactionReply.DECLINED);
			return reply;
		}



		System.out.println("Bank ID: " + request.getBankId());
		System.out.println("Amount: " + request.getAmount());
		System.out.println("Merchant: " + request.getMerchant());
		System.out.println("AccountNumber: " + decrypt(secret, request.getAccount().getNumber()));
		System.out.println("AccountMonthExp: " + request.getAccount().getMonthExp());
		System.out.println("AccountYearExp: " + request.getAccount().getYearExp());
		System.out.println("CVV: " + decrypt(secret, request.getAccount().getCvv()));



		reply.setResult(TransactionReply.ACCEPTED);
		return reply;
	}

	public boolean verifyAPIKey(String apiKey) {
		if(paymentBrokerRepository.findByApiKey(apiKey) == null)
			return false;

		return true;
	}

	// Source https://stackoverflow.com/questions/15554296/simple-java-aes-encrypt-decrypt-example
	public static String encrypt(String key, String value) {
		try {
			IvParameterSpec iv = new IvParameterSpec(new byte[16]);
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			System.out.println("encrypted string: "
					+ Base64.encodeBase64String(encrypted));

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
	// Source https://stackoverflow.com/questions/15554296/simple-java-aes-encrypt-decrypt-example
	public static String decrypt(String key, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(new byte[16]);
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

}
