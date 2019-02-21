package com.ets.gti525.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ets.gti525.domain.entity.DebitCardTransaction;

/**
 * Description : JPA repository for debit card transactions in the database.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 23-01-2019
 */
public interface DebitCardTransactionRepository extends JpaRepository<DebitCardTransaction, Integer>{
	@Query(value = "SELECT * FROM DEBIT_CARD_TRANSACTION t WHERE t.DEBIT_CARD_NBR = ?1", nativeQuery = true)
	public List<DebitCardTransaction> findByDebitCardNbr(long debitCardNbr);
}
