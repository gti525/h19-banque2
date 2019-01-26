package com.ets.gti525.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ets.gti525.domain.entity.CreditCard;
import com.ets.gti525.domain.entity.DebitCard;
import com.ets.gti525.domain.repository.CreditCardRepository;
import com.ets.gti525.domain.repository.CreditCardTransactionRepository;
import com.ets.gti525.domain.repository.DebitCardRepository;
import com.ets.gti525.domain.repository.DebitCardTransactionRepository;
import com.ets.gti525.domain.response.CreditCardInfoResponse;
import com.ets.gti525.domain.response.DebitCardInfoResponse;

@Service
public class AccountService {

	private final CreditCardRepository creditCardRepository;
	private final CreditCardTransactionRepository creditCardTransactionRepository;
	private final DebitCardRepository debitCardRepository;
	private final DebitCardTransactionRepository debitCardTransactionRepository;
	
	public AccountService(final CreditCardRepository creditCardRepository,
			final CreditCardTransactionRepository transactionRepository,
			final DebitCardRepository debitCardRepository,
			final DebitCardTransactionRepository debitCardTransactionRepository) {
		this.creditCardRepository = creditCardRepository;
		this.creditCardTransactionRepository = transactionRepository;
		this.debitCardRepository = debitCardRepository;
		this.debitCardTransactionRepository = debitCardTransactionRepository;
	}
	
	public CreditCardInfoResponse getCreditCardInfo(long nbr) {
		CreditCard creditCard = creditCardRepository.findByNbr(nbr);
		CreditCardInfoResponse response = new CreditCardInfoResponse(HttpStatus.OK,
				creditCard.getNumber(),
				creditCard.getBalance(),
				creditCard.getLimit(),
				creditCard.getMonthExp(),
				creditCard.getYearExp());
		
		return response;
	}
	
	public DebitCardInfoResponse getDebitCardInfo(long nbr) {
		DebitCard debitCard = debitCardRepository.findByNbr(nbr);
		DebitCardInfoResponse response = new DebitCardInfoResponse(HttpStatus.OK,
				debitCard.getNbr(),
				debitCard.getBalance());
		
		return response;
	}
}
