package com.ets.gti525.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ets.gti525.domain.entity.CreditCard;
import com.ets.gti525.domain.entity.DebitCard;
import com.ets.gti525.domain.entity.User;
import com.ets.gti525.domain.repository.CreditCardRepository;
import com.ets.gti525.domain.repository.DebitCardRepository;
import com.ets.gti525.domain.response.CreditCardInfoResponse;
import com.ets.gti525.domain.response.DebitCardInfoResponse;

@Service
public class AccountService {

	private final CreditCardRepository creditCardRepository;
	private final DebitCardRepository debitCardRepository;

	@Value("${com.ets.gti525.security.ownershipCheck}")
	private String ownershipCheck;

	public AccountService(final CreditCardRepository creditCardRepository,
			final DebitCardRepository debitCardRepository) {
		this.creditCardRepository = creditCardRepository;
		this.debitCardRepository = debitCardRepository;
	}

	public CreditCardInfoResponse getCreditCardInfo(long nbr) {
		CreditCard creditCard = creditCardRepository.findByNbr(nbr);
		
		if(creditCard == null) 
			return new CreditCardInfoResponse(HttpStatus.NOT_FOUND);


		if(isOwnershipCheck()) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = (User) auth.getPrincipal();
			if(creditCard.getOwner().equals(user) == false) {
				return new CreditCardInfoResponse(HttpStatus.UNAUTHORIZED);
			}
		}

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

		if(isOwnershipCheck()) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = (User) auth.getPrincipal();
			if(debitCard.getOwner().equals(user) == false) {
				return new DebitCardInfoResponse(HttpStatus.UNAUTHORIZED);
			}
		}

		DebitCardInfoResponse response = new DebitCardInfoResponse(HttpStatus.OK,
				debitCard.getNbr(),
				debitCard.getBalance());

		return response;
	}

	private boolean isOwnershipCheck() {
		if(ownershipCheck == "true")
			return true;
		return false;
	}
}
