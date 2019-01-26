package com.ets.gti525.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ets.gti525.domain.entity.DebitCard;

public interface DebitCardRepository extends JpaRepository<DebitCard, Long> {
	
	public DebitCard findByNbr(long debitCardNbr);
}
