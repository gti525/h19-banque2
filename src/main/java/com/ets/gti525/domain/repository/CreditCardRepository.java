package com.ets.gti525.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ets.gti525.domain.entity.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

	public CreditCard findByNbr(long creditCardNbr);
}
