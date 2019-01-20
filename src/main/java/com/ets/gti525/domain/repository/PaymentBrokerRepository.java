package com.ets.gti525.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ets.gti525.domain.entity.PaymentBroker;

public interface PaymentBrokerRepository extends JpaRepository<PaymentBroker, Integer>{

	PaymentBroker findByApiKey(String apiKey);
	
	

}
