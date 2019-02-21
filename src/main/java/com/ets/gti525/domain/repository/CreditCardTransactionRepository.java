package com.ets.gti525.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ets.gti525.domain.entity.CreditCardTransaction;

/**
 * Description : JPA repository for credit card transactions in the database.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 20-01-2019
 */
public interface CreditCardTransactionRepository extends JpaRepository<CreditCardTransaction, Integer> {
	@Query(value = "SELECT * FROM CREDIT_CARD_TRANSACTION t WHERE t.CREDIT_CARD_NBR = ?1", nativeQuery = true)
	public List<CreditCardTransaction> findByCreditCardNbr(long creditCardNbr);

	public List<CreditCardTransaction> findByIsPreauth(boolean b);
}
