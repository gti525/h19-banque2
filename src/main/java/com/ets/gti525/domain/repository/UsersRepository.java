package com.ets.gti525.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ets.gti525.domain.entity.User;

public interface UsersRepository extends JpaRepository<User, Long> {
	
	public User findById(int id);
	
	public List<User> findByUsername(String username);
	
	@Query(value = "SELECT * FROM USERS u WHERE u.USERNAME LIKE %?1%", nativeQuery = true)
	public List<User> findByFirstnameKeyword(String keyword);
	
	@Query(value = "SELECT * FROM USER "
			+ "INNER JOIN DEBIT_CARD ON USER.ID = DEBIT_CARD.OWNER_ID "
			+ "INNER JOIN CREDIT_CARD ON USER.ID = CREDIT_CARD.OWNER_ID "
			+ "WHERE "
			+ "UPPER(USER.FIRST_NAME) LIKE %?1% OR "
			+ "UPPER(USER.LAST_NAME) LIKE %?1% OR "
			+ "UPPER(DEBIT_CARD.NBR) LIKE %?1% OR "
			+ "UPPER(CREDIT_CARD.NBR) LIKE %?1%", nativeQuery = true)
	public List<User> findByKeyword(String keyword);
}
