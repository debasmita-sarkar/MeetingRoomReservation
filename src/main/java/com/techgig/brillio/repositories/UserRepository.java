package com.techgig.brillio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techgig.brillio.model.Building;
import com.techgig.brillio.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findById(int id);

	public User findByName(String name);
	
	public User findByEmail(String email);
}
