package com.ets.gti525.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ets.gti525.domain.entity.CreditCard;
import com.ets.gti525.domain.entity.DebitCard;
import com.ets.gti525.domain.repository.CreditCardRepository;
import com.ets.gti525.domain.repository.DebitCardRepository;
import com.ets.gti525.domain.response.CreditCardInfoResponse;
import com.ets.gti525.domain.response.DebitCardInfoResponse;

/**
 * Description : Service containing operations related to accounts.
 * (Usually called by controllers)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 25-01-2019
 */
@Service
public class AccountService {

	private final CreditCardRepository creditCardRepository;
	private final DebitCardRepository debitCardRepository;


	public AccountService(final CreditCardRepository creditCardRepository,
			final DebitCardRepository debitCardRepository) {
		this.creditCardRepository = creditCardRepository;
		this.debitCardRepository = debitCardRepository;
	}

	public CreditCardInfoResponse getCreditCardInfo(long nbr) {
		CreditCard creditCard = creditCardRepository.findByNbr(nbr);

		if(creditCard == null) 
			return new CreditCardInfoResponse(HttpStatus.NOT_FOUND);


		CreditCardInfoResponse response = new CreditCardInfoResponse(HttpStatus.OK,
				creditCard.getNumber(),
				creditCard.getBalance(),
				creditCard.getLimit(),
				creditCard.getMonthExp(),
				creditCard.getYearExp());

		return response;
	}

	public CreditCardInfoResponse getMyCreditCardInfo() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user;

		try {
			user = auth.getPrincipal().toString();
		} catch (Exception e) {
			return new CreditCardInfoResponse(HttpStatus.UNAUTHORIZED);
		}

		CreditCard creditCard = creditCardRepository.findByOwner_username(user);

		if(creditCard == null) 
			return new CreditCardInfoResponse(HttpStatus.NOT_FOUND);


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

		if(debitCard == null) 
			return new DebitCardInfoResponse(HttpStatus.NOT_FOUND);


		DebitCardInfoResponse response = new DebitCardInfoResponse(HttpStatus.OK,
				debitCard.getNbr(),
				debitCard.getBalance());

		return response;
	}

	public DebitCardInfoResponse getMyDebitCardInfo() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user;

		try {
			user = auth.getPrincipal().toString();
		} catch (Exception e) {
			return new DebitCardInfoResponse(HttpStatus.UNAUTHORIZED);
		}

		DebitCard debitCard = debitCardRepository.findByOwner_username(user);

		if(debitCard == null) 
			return new DebitCardInfoResponse(HttpStatus.NOT_FOUND);


		DebitCardInfoResponse response = new DebitCardInfoResponse(HttpStatus.OK,
				debitCard.getNbr(),
				debitCard.getBalance());

		return response;
	}



}
