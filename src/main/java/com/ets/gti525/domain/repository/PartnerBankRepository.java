package com.ets.gti525.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ets.gti525.domain.entity.PartnerBank;

public interface PartnerBankRepository extends JpaRepository<PartnerBank, Integer>{

	PartnerBank findByApiKey(String apiKey);

}
