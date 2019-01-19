package com.ets.gti525.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ets.gti525.domain.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	
	public Users findById(int id);
	
	public List<Users> findByUsername(String username);
	
	@Query(nativeQuery = true, value = "SELECT * FROM USERS WHERE ID = 1")
	public Users getUser();

}
