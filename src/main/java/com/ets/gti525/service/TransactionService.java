package com.ets.gti525.service;

import java.sql.Timestamp;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ets.gti525.domain.entity.CreditCard;
import com.ets.gti525.domain.entity.CreditCardTransaction;
import com.ets.gti525.domain.entity.DebitCard;
import com.ets.gti525.domain.entity.DebitCardTransaction;
import com.ets.gti525.domain.repository.CreditCardRepository;
import com.ets.gti525.domain.repository.CreditCardTransactionRepository;
import com.ets.gti525.domain.repository.DebitCardRepository;
import com.ets.gti525.domain.repository.DebitCardTransactionRepository;
import com.ets.gti525.domain.repository.PaymentBrokerRepository;
import com.ets.gti525.domain.request.CreditCardPaymentRequest;
import com.ets.gti525.domain.request.CreditCardTransactionRequest;
import com.ets.gti525.domain.response.TransactionReply;

@Service
public class TransactionService {

	private final PaymentBrokerRepository paymentBrokerRepository;
	private final CreditCardRepository creditCardRepository;
	private final CreditCardTransactionRepository creditCardTransactionRepository;
	private final DebitCardRepository debitCardRepository;
	private final DebitCardTransactionRepository debitCardTransactionRepository;

	public TransactionService(final PaymentBrokerRepository paymentBrokerRepository,
			final CreditCardRepository creditCardRepository,
			final CreditCardTransactionRepository transactionRepository,
			final DebitCardRepository debitCardRepository,
			final DebitCardTransactionRepository debitCardTransactionRepository) {
		this.paymentBrokerRepository = paymentBrokerRepository;
		this.creditCardRepository = creditCardRepository;
		this.creditCardTransactionRepository = transactionRepository;
		this.debitCardRepository = debitCardRepository;
		this.debitCardTransactionRepository = debitCardTransactionRepository;
	}

	public static final String BANK_2_ID = "9bb9426e-f176-4a76-9be5-68709325e43c";

	public TransactionReply processCCTransaction(String apiKey, CreditCardTransactionRequest request) {
		TransactionReply reply = new TransactionReply();
		String secret = paymentBrokerRepository.findByApiKey(apiKey).getSecret();

		if(!request.getBankId().equals(BANK_2_ID)) {
			reply.setResult(TransactionReply.DECLINED);
			return reply;
		}

		String accountNumber = decrypt(secret, request.getAccount().getNumber());
		CreditCard cc = creditCardRepository.findById(Long.parseLong(accountNumber)).get();

		if(cc == null) {
			reply.setResult(TransactionReply.DECLINED);
			return reply;
		}

		String cvv = decrypt(secret, request.getAccount().getCvv());

		if(cc.getMonthExp() != request.getAccount().getMonthExp() ||
				cc.getYearExp() != request.getAccount().getYearExp() ||
				!String.valueOf(cc.getCvv()).equals(cvv)){

			reply.setResult(TransactionReply.DECLINED);
			return reply;
		}

		CreditCardTransaction transaction = new CreditCardTransaction();
		transaction.setAmount(request.getAmount());
		transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
		
		if(request.getAmount() >= 0)
			transaction.setDescription("Achat " + request.getMerchant());
		else
			transaction.setDescription("Remboursement " + request.getMerchant());


		if(cc.addTransaction(transaction)) {
			reply.setResult(TransactionReply.ACCEPTED);
			creditCardTransactionRepository.save(transaction);
			creditCardRepository.save(cc);
		}
		else
			reply.setResult(TransactionReply.DECLINED_INSUFFICIANT_FUNDS);

		

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
	
	
	public TransactionReply processCreditCardPayment(CreditCardPaymentRequest request) {
		TransactionReply reply = new TransactionReply();
		
		DebitCardTransaction debitTransaction;
		CreditCardTransaction creditTransaction;
		
		DebitCard debitCard = debitCardRepository.findById(Long.parseLong(request.getSourceDebitCardNumber())).get();
		CreditCard creditCard = creditCardRepository.findById(Long.parseLong(request.getTargetCreditCardNumber())).get();
		
		if(debitCard == null || creditCard == null) {
			reply.setResult(TransactionReply.DECLINED);
			return reply;
		}
		
		
		// Validation additionnelle a ajouter: Est-ce que ce user est propriétaire de ces 2 comptes ?
		// Sinon, c'est potentiellement un crosseur
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//authentication. do something here
		
		

		if(request.getAmount() > 0 &&request.getAmount() > debitCard.getBalance()) {
			reply.setResult(TransactionReply.DECLINED_INSUFFICIANT_FUNDS);
			return reply;
		}
		
		debitTransaction = new DebitCardTransaction();
		debitTransaction.setAmount(request.getAmount());
		debitTransaction.setDescription(DebitCardTransaction.PAYMENT_OF_CREDIT_CARD);
		debitTransaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
		
		creditTransaction = new CreditCardTransaction();
		creditTransaction.setAmount(request.getAmount() * -1);
		creditTransaction.setDescription(CreditCardTransaction.PAYMENT_OF_CREDIT_CARD);
		creditTransaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
		
		if( debitCard.addTransaction(debitTransaction) &&
				creditCard.addTransaction(creditTransaction)) {
			
			debitCardTransactionRepository.save(debitTransaction);
			debitCardRepository.save(debitCard);
			
			creditCardTransactionRepository.save(creditTransaction);
			creditCardRepository.save(creditCard);
		
			reply.setResult(TransactionReply.ACCEPTED);
		}

		return reply;
			
	}
	
}
