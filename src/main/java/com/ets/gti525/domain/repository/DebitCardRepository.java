package com.ets.gti525.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ets.gti525.domain.entity.DebitCard;

/**
 * Description : JPA repository for debit cards in the database.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 23-01-2019
 */
public interface DebitCardRepository extends JpaRepository<DebitCard, Long> {
	
	public DebitCard findByNbr(long debitCardNbr);
	
	public DebitCard findByOwnerId(int ownerId);

	public DebitCard findByOwner_username(String user);
}
