package com.ets.gti525.service;

import java.sql.Timestamp;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ets.gti525.domain.entity.CreditCard;
import com.ets.gti525.domain.entity.CreditCardTransaction;
import com.ets.gti525.domain.entity.DebitCard;
import com.ets.gti525.domain.entity.DebitCardTransaction;
import com.ets.gti525.domain.entity.PartnerBank;
import com.ets.gti525.domain.entity.PaymentBroker;
import com.ets.gti525.domain.repository.CreditCardRepository;
import com.ets.gti525.domain.repository.CreditCardTransactionRepository;
import com.ets.gti525.domain.repository.DebitCardRepository;
import com.ets.gti525.domain.repository.DebitCardTransactionRepository;
import com.ets.gti525.domain.repository.PartnerBankRepository;
import com.ets.gti525.domain.repository.PaymentBrokerRepository;
import com.ets.gti525.domain.request.BankTransferRequest;
import com.ets.gti525.domain.request.BankTransferRequestBank1;
import com.ets.gti525.domain.request.CreditCardPaymentRequest;
import com.ets.gti525.domain.request.PreAuthCCTransactionRequest;
import com.ets.gti525.domain.request.ProcessCCTransactionRequest;
import com.ets.gti525.domain.response.AbstractResponse;
import com.ets.gti525.domain.response.CreditCardTransactionsResponse;
import com.ets.gti525.domain.response.DebitCardTransactionsResponse;
import com.ets.gti525.domain.response.EmptyResponse;
import com.ets.gti525.domain.response.PreAuthReply;
import com.ets.gti525.domain.response.ProcessCCReply;
import com.ets.gti525.domain.response.TransactionResponse;

/**
 * Description : Service containing operations related to transactions.
 * (Usually called by controllers)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 16-01-2019
 */
@Service
public class TransactionService {

	private final PaymentBrokerRepository paymentBrokerRepository;
	private final CreditCardRepository creditCardRepository;
	private final CreditCardTransactionRepository creditCardTransactionRepository;
	private final DebitCardRepository debitCardRepository;
	private final DebitCardTransactionRepository debitCardTransactionRepository;
	private final PartnerBankRepository partnerBankRepository;
	private final RestTemplate restTemplate;

	@Value("${com.ets.gti525.transaction.preAuthValidTimeMs}")
	private long preAuthValidTimeMs;

	public TransactionService(final PaymentBrokerRepository paymentBrokerRepository,
			final CreditCardRepository creditCardRepository,
			final CreditCardTransactionRepository transactionRepository,
			final DebitCardRepository debitCardRepository,
			final DebitCardTransactionRepository debitCardTransactionRepository,
			final PartnerBankRepository partnerBankRepository,
			RestTemplateBuilder restTemplateBuilder) {
		this.paymentBrokerRepository = paymentBrokerRepository;
		this.creditCardRepository = creditCardRepository;
		this.creditCardTransactionRepository = transactionRepository;
		this.debitCardRepository = debitCardRepository;
		this.debitCardTransactionRepository = debitCardTransactionRepository;
		this.partnerBankRepository = partnerBankRepository;
		this.restTemplate = restTemplateBuilder.build();
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


	public TransactionResponse processCreditCardPayment(CreditCardPaymentRequest request) {
		TransactionResponse reply;

		DebitCardTransaction debitTransaction;
		CreditCardTransaction creditTransaction;
		DebitCard debitCard;
		CreditCard creditCard;

		try {
			debitCard = debitCardRepository.findById(Long.parseLong(request.getSourceDebitCardNumber())).get();
			creditCard = creditCardRepository.findById(Long.parseLong(request.getTargetCreditCardNumber())).get();
		}catch(Exception e) {
			return new TransactionResponse(HttpStatus.BAD_REQUEST, TransactionResponse.DECLINED);
		}

		if(debitCard == null || creditCard == null) {
			return new TransactionResponse(HttpStatus.OK, TransactionResponse.DECLINED);

		}


		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user;

		try {
			user = auth.getPrincipal().toString();
		} catch (Exception e) {
			return new TransactionResponse(HttpStatus.UNAUTHORIZED, null);
		}


		if(creditCard.getOwner().getUsername().equalsIgnoreCase(user) == false ||
				debitCard.getOwner().getUsername().equalsIgnoreCase(user) == false) {
			reply = new TransactionResponse(HttpStatus.UNAUTHORIZED, null);
			return reply;
		}


		if(request.getAmount() > 0 && request.getAmount() > debitCard.getBalance()) {
			reply = new TransactionResponse(HttpStatus.OK, TransactionResponse.DECLINED_INSUFFICIANT_FUNDS);
			return reply;
		}

		debitTransaction = new DebitCardTransaction();
		debitTransaction.setAmount(request.getAmount() * -1);
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

			reply = new TransactionResponse(HttpStatus.OK, TransactionResponse.ACCEPTED);
			return reply;
		}

		reply = new TransactionResponse(HttpStatus.BAD_REQUEST, null);
		return reply;

	}

	public CreditCardTransactionsResponse getCreditCardTransactions(long nbr) {

		CreditCard cc = creditCardRepository.findByNbr(nbr);
		if(cc == null) {
			return new CreditCardTransactionsResponse(HttpStatus.BAD_REQUEST, null);
		}


		List<CreditCardTransaction> transactions = creditCardTransactionRepository.findByCreditCardNbr(nbr);
		return new CreditCardTransactionsResponse(HttpStatus.OK, transactions);

	}

	public CreditCardTransactionsResponse getMyCreditCardTransactions() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user;

		try {
			user = auth.getPrincipal().toString();
		} catch (Exception e) {
			return new CreditCardTransactionsResponse(HttpStatus.UNAUTHORIZED, null);
		}

		CreditCard cc = creditCardRepository.findByOwner_username(user);

		if(cc == null)
			return new CreditCardTransactionsResponse(HttpStatus.UNAUTHORIZED, null);

		List<CreditCardTransaction> transactions = creditCardTransactionRepository.findByCreditCardNbr(cc.getNbr());
		return new CreditCardTransactionsResponse(HttpStatus.OK, transactions);	

	}

	public DebitCardTransactionsResponse getDebitCardTransactions(long nbr) {
		DebitCard dc = debitCardRepository.findByNbr(nbr);
		if(dc == null) {
			return new DebitCardTransactionsResponse(HttpStatus.BAD_REQUEST, null);
		}

		List<DebitCardTransaction> transactions = debitCardTransactionRepository.findByDebitCardNbr(nbr);
		return new DebitCardTransactionsResponse(HttpStatus.OK, transactions);

	}

	public DebitCardTransactionsResponse getMyDebitCardTransactions() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user;

		try {
			user = auth.getPrincipal().toString();
		} catch (Exception e) {
			return new DebitCardTransactionsResponse(HttpStatus.UNAUTHORIZED, null);
		}

		DebitCard dc = debitCardRepository.findByOwner_username(user);

		if(dc == null)
			return new DebitCardTransactionsResponse(HttpStatus.UNAUTHORIZED, null);

		List<DebitCardTransaction> transactions = debitCardTransactionRepository.findByDebitCardNbr(dc.getNbr());
		return new DebitCardTransactionsResponse(HttpStatus.OK, transactions);	

	}


	public TransactionResponse processBankTransfer(BankTransferRequest request, String apiKey) {
		DebitCard sourceDC = debitCardRepository.findByNbr(request.getSourceAccountNumber());

		if(sourceDC == null || request.getAmount() <=0) {
			return new TransactionResponse(HttpStatus.BAD_REQUEST, TransactionResponse.DECLINED);
		}

		PaymentBroker pg = paymentBrokerRepository.findByApiKey(apiKey);


		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userString = null;

		try {
			userString = auth.getPrincipal().toString();
		} catch (Exception e) {
			// Do nothing
		}

		if(sourceDC.getOwner().getUsername().equals(userString) == false || pg != null) {
			return new TransactionResponse(HttpStatus.UNAUTHORIZED, TransactionResponse.DECLINED);
		}

		DebitCardTransaction senderTransaction;
		DebitCardTransaction recipientTransaction;

		// Verify the decimal length
		// https://www.quora.com/Is-there-is-any-way-to-find-the-number-of-digits-after-a-decimal-in-a-floating-number-stored-in-an-array-with-complexity-n
		if(String.valueOf(request.getAmount()).split("\\.")[1].length() > 2)
			return new TransactionResponse(HttpStatus.UNAUTHORIZED, TransactionResponse.DECLINED);

		if(sourceDC.getBalance() < request.getAmount())
			return new TransactionResponse(HttpStatus.PRECONDITION_FAILED, TransactionResponse.DECLINED_INSUFFICIANT_FUNDS);


		DebitCard destDC = debitCardRepository.findByNbr(request.getTargetAccountNumber());

		if(destDC == null) {

			String destPrefix;
			try {
				destPrefix = String.valueOf(request.getTargetAccountNumber()).substring(0, 3);
			} catch (Exception e) {
				return new TransactionResponse(HttpStatus.BAD_REQUEST, TransactionResponse.DECLINED);
				//Malformed target account
			}

			for(PartnerBank pb : partnerBankRepository.findAll()) {

				if(pb.getAccountPrefix().equals(destPrefix)) {
					
					boolean status = initiateBankTransfer(request);

					if(status) {

						senderTransaction = new DebitCardTransaction();
						senderTransaction.setAmount(request.getAmount() * -1);
						senderTransaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
						senderTransaction.setDescription("Virement vers " + pb.getName() + " | compte "
								+ request.getTargetAccountNumber());
						sourceDC.addTransaction(senderTransaction);
						debitCardRepository.save(sourceDC);

						return new TransactionResponse(HttpStatus.OK, TransactionResponse.ACCEPTED);
					}
					else {
						return new TransactionResponse(HttpStatus.EXPECTATION_FAILED, TransactionResponse.TARGET_BANK_FAILURE);
					}

				}

			}

			return new TransactionResponse(HttpStatus.BAD_REQUEST, "DESTINATION_ACCOUNT_INVALID");
		}



		senderTransaction = new DebitCardTransaction();
		senderTransaction.setAmount(request.getAmount() * -1);
		senderTransaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
		senderTransaction.setDescription("Virement vers " + destDC.getOwner().getUsername());

		recipientTransaction = new DebitCardTransaction();
		recipientTransaction.setAmount(request.getAmount());
		recipientTransaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
		recipientTransaction.setDescription("Virement reçu de " + sourceDC.getOwner().getUsername());


		if( sourceDC.addTransaction(senderTransaction) &&
				destDC.addTransaction(recipientTransaction)) {

			debitCardTransactionRepository.save(senderTransaction);
			debitCardRepository.save(sourceDC);
			debitCardTransactionRepository.save(recipientTransaction);
			debitCardRepository.save(destDC);

			return new TransactionResponse(HttpStatus.OK, TransactionResponse.ACCEPTED);

		}

		return new TransactionResponse(HttpStatus.BAD_REQUEST, null);

	}


	public PreAuthReply preAuthCCTransaction(String apiKey, PreAuthCCTransactionRequest request) {

		PreAuthReply reply;
		CreditCard cc = null;
		String secret = paymentBrokerRepository.findByApiKey(apiKey).getSecret();
		String cardholderNameInRequest = request.getAccount().getCardholderName();

		if(request.getAccount().getNumber() == null || cardholderNameInRequest == null ||
				request.getAmount() <= 0)
			return new PreAuthReply(HttpStatus.BAD_REQUEST, PreAuthReply.DECLINED, null);


		String accountNumber = decrypt(secret, request.getAccount().getNumber());

		try{
			cc = creditCardRepository.findById(Long.parseLong(accountNumber)).get();
		} catch(Exception e) {
			return new PreAuthReply(HttpStatus.OK, PreAuthReply.DECLINED, null);
		}

		// Verify the decimal length
		// https://www.quora.com/Is-there-is-any-way-to-find-the-number-of-digits-after-a-decimal-in-a-floating-number-stored-in-an-array-with-complexity-n
		if(verifyAmountFormat(request.getAmount()) == false)
			return new PreAuthReply(HttpStatus.BAD_REQUEST, PreAuthReply.DECLINED, null);

		if(cardholderNameInRequest.equalsIgnoreCase(cc.getOwner().getCardholderName()) == false)
			return new PreAuthReply(HttpStatus.BAD_REQUEST, PreAuthReply.DECLINED, null);
		
		if(request.getAmount() <= 0)
			return new PreAuthReply(HttpStatus.BAD_REQUEST, "Refunds are not supported", null);


		String cvv = decrypt(secret, request.getAccount().getCvv());

		String[] expAsString = request.getAccount().getExp().split("/");
		if(expAsString.length != 2)
			return new PreAuthReply(HttpStatus.BAD_REQUEST, PreAuthReply.DECLINED, null);

		int monthExp;
		int yearExp;

		try{
			monthExp = Integer.parseInt(expAsString[0]);
			yearExp = Integer.parseInt(expAsString[1]);
		}catch(Exception e) {
			return new PreAuthReply(HttpStatus.BAD_REQUEST, PreAuthReply.DECLINED, null); 
		}

		if(cc.getMonthExp() != monthExp ||
				cc.getYearExp() != yearExp ||
				!String.valueOf(cc.getCvv()).equals(cvv)){

			return new PreAuthReply(HttpStatus.OK, PreAuthReply.DECLINED, null);
		}

		CreditCardTransaction transaction = new CreditCardTransaction();
		transaction.setAmount(request.getAmount());
		transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
		transaction.setPreauth(true);
		transaction.setDescription("Achat " + request.getMerchantDesc());
		transaction.setTargetMerchantNumber(request.getMerchantAccountNumber());

		if(cc.addTransaction(transaction)) {
			transaction = creditCardTransactionRepository.save(transaction);
			creditCardRepository.save(cc);

			reply = new PreAuthReply(HttpStatus.OK, PreAuthReply.ACCEPTED, transaction.getId());
			return reply;
		}
		else {
			return new PreAuthReply(HttpStatus.OK, PreAuthReply.DECLINED_INSUFFICIANT_FUNDS, null);

		}


	}



	private boolean verifyAmountFormat(double amount) {
		return String.valueOf(amount).split("\\.")[1].length() <= 2;
	}

	@Scheduled(fixedRateString = "${com.ets.gti525.transaction.preAuthCleanupTimeMs}")
	private void cleanExpiredPreAuth(){
		System.out.println("Cleaning up expired pre-auths");
		List<CreditCardTransaction> preAuths = creditCardTransactionRepository.findByIsPreauth(true);

		for(CreditCardTransaction t: preAuths) {
			if(System.currentTimeMillis() > t.getTimestamp().getTime() + preAuthValidTimeMs) {
				CreditCard cc = t.getCreditCard();
				cc.removeTransaction(t);
				creditCardRepository.save(cc);
				creditCardTransactionRepository.delete(t);
			}
		}

	}






	public ProcessCCReply processCCTransaction(ProcessCCTransactionRequest request) {

		if(request.getTransactionID() == null || request.validAction() == false)
			return new ProcessCCReply(HttpStatus.BAD_REQUEST, null);

		CreditCardTransaction transaction = creditCardTransactionRepository.findById(request.getTransactionID()).get();
		if(transaction == null)
			return new ProcessCCReply(HttpStatus.NOT_FOUND, null);



		if(request.getAction().equals(ProcessCCTransactionRequest.ACTION_COMMIT) &&
				System.currentTimeMillis() < transaction.getTimestamp().getTime() + preAuthValidTimeMs) {
			
			DebitCard dc = debitCardRepository.findByNbr(transaction.getTargetMerchantNumber());
			if(dc != null) {
				// Target est dans banque2
				DebitCardTransaction dcTrans = new DebitCardTransaction();
				dcTrans.setTimestamp(transaction.getTimestamp());
				dcTrans.setAmount(transaction.getAmount());
				dcTrans.setDescription("Réception de fonds de la transaction crédit # " + transaction.getId());
				dc.addTransaction(dcTrans);
				transaction.setPreauth(false);
				creditCardTransactionRepository.save(transaction);
				debitCardRepository.save(dc);
				return new ProcessCCReply(HttpStatus.OK, ProcessCCReply.STATUS_COMMITED);
			}
			else {
				// Target ailleurs
				BankTransferRequest btr = new BankTransferRequest();
				btr.setAmount(transaction.getAmount());
				btr.setTargetAccountNumber(transaction.getTargetMerchantNumber());
				btr.setSourceAccountNumber(22200000);
				
				if(initiateBankTransfer(btr)) {
					transaction.setPreauth(false);
					creditCardTransactionRepository.save(transaction);
					return new ProcessCCReply(HttpStatus.OK, ProcessCCReply.STATUS_COMMITED);
				}
				else {
					return new ProcessCCReply(HttpStatus.OK, ProcessCCReply.STATUS_DECLINED_BY_3RD_PARTY_BANK);
				}
			}
			
			
			
		}
		else if(request.getAction().equals(ProcessCCTransactionRequest.ACTION_CANCEL)) {
			if(transaction.isPreauth() && transaction.getCreditCard().removeTransaction(transaction)) {
				creditCardRepository.save(transaction.getCreditCard());
				creditCardTransactionRepository.delete(transaction);
				return new ProcessCCReply(HttpStatus.OK, ProcessCCReply.STATUS_CANCELLED);
			}
			else
				return new ProcessCCReply(HttpStatus.NOT_FOUND, null);
		}

		return new ProcessCCReply(HttpStatus.EXPECTATION_FAILED, null);


	}



	public AbstractResponse receiveBankToBankTransfer(String apiKey, BankTransferRequest request) {

		PartnerBank pb = partnerBankRepository.findByApiKey(apiKey);
		if(pb == null)
			return new EmptyResponse(HttpStatus.UNAUTHORIZED, "Bad authentication");

		DebitCard destAccount = debitCardRepository.findByNbr(request.getTargetAccountNumber());
		if(destAccount == null)
			return new EmptyResponse(HttpStatus.NOT_FOUND, "Invalid destination account");

		Double amount = request.getAmount();
		if(verifyAmountFormat(amount) == false || amount < 0)
			return new EmptyResponse(HttpStatus.BAD_REQUEST, "Invalid amount");

		DebitCardTransaction t = new DebitCardTransaction();
		t.setTimestamp(new Timestamp(System.currentTimeMillis()));
		t.setAmount(amount);
		t.setDescription("Virement reçu de " + pb.getName());

		destAccount.addTransaction(t);
		debitCardRepository.save(destAccount);

		return new EmptyResponse(HttpStatus.NO_CONTENT, null);

	}

	/**
	 * 
	 * Source https://www.baeldung.com/rest-template
	 */
	private boolean initiateBankTransfer(BankTransferRequest request) {

		PartnerBank pb = partnerBankRepository.findByAccountPrefix(
				String.valueOf(request.getTargetAccountNumber()).substring(0, 3)
				);

		if(pb == null)
			return false;

		ResponseEntity<String> response;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("apikey", pb.getApiKeyToUse());
			HttpEntity<BankTransferRequestBank1> httpRequest = new HttpEntity<>(new BankTransferRequestBank1(request), headers);
			response = restTemplate.postForEntity(
					pb.getPostUrlToUse(), httpRequest, String.class);
		} catch (RestClientException e) {
			e.printStackTrace();
			return false;
		}

		if(response.getStatusCode() == HttpStatus.OK)
			return true;

		return false;
	}


}
