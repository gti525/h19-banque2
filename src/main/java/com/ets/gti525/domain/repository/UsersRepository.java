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
}
