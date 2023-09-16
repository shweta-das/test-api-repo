package com.synchrony.rest.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.synchrony.rest.api.exception.ResourceNotFoundException;
import com.synchrony.rest.api.exception.UnexpectedException;
import com.synchrony.rest.api.model.User;
import com.synchrony.rest.api.repository.UserRepository;
import com.synchrony.rest.api.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User loginUser(User loginUser) {
		logger.info(loginUser.toString());

		try {
			return Optional.of(this.userRepo.findByUsername(loginUser.getUsername()))
					.orElseThrow(() -> new ResourceNotFoundException("User " + loginUser.getUsername() + " not found"));
			
		} catch (Exception ex) {
			throw new UnexpectedException("Exception in loginUser Method " +  loginUser.getUsername());
		}
	}

	@Override
	public User registerUser(User user) {
		logger.info("registerUser  " + user);

		try {
			
			return Optional.of(this.userRepo.save(user)).orElseThrow(() -> new ResourceAccessException("Incomplete Registration") );
			
		}catch(Exception ex) {
			throw new UnexpectedException("Exception in registerUser Method");
		}
	}

	@Override
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

	
	@Override
	public User getUser(long id) {
		try {
			return this.userRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));
			
		} catch (Exception ex) {
			throw new UnexpectedException("Exception in getUser Method " +  id);
		}
	}


}
