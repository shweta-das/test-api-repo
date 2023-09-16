package com.synchrony.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synchrony.rest.api.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsername(String username);

}
