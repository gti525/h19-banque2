package com.ets.gti525.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ets.gti525.domain.entity.CreditCardTransaction;

public interface CreditCardTransactionRepository extends JpaRepository<CreditCardTransaction, Integer> {
	@Query(value = "SELECT * FROM CREDIT_CARD_TRANSACTION t WHERE t.CREDIT_CARD_NBR = ?1", nativeQuery = true)
	public List<CreditCardTransaction> findByCreditCardNbr(long creditCardNbr);
}
