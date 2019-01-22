package com.ets.gti525.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ets.gti525.domain.entity.CreditCardTransaction;

public interface CreditCardTransactionRepository extends JpaRepository<CreditCardTransaction, Integer> {

}
