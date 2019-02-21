package com.ets.gti525.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ets.gti525.domain.entity.PaymentBroker;

/**
 * Description : JPA repository for payment brokers in the database.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 20-01-2019
 */
public interface PaymentBrokerRepository extends JpaRepository<PaymentBroker, Integer>{
	PaymentBroker findByApiKey(String apiKey);
}
