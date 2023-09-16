package com.synchrony.rest.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synchrony.rest.api.model.User;

@Service
public interface UserService{

	public User registerUser(User user);
	public User loginUser(User user);
	public User getUser(long id);
	public List<User> getUsers();
}
