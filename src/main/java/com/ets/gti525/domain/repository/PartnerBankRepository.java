package com.ets.gti525.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ets.gti525.domain.entity.PartnerBank;

/**
 * Description : JPA repository for partner banks in the database.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 09-02-2019
 */
public interface PartnerBankRepository extends JpaRepository<PartnerBank, Integer>{

	PartnerBank findByApiKey(String apiKey);

	PartnerBank findByAccountPrefix(String substring);

}
