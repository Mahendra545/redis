package com.example.redis.service;

import com.example.redis.entity.User;

public interface UserService {

	public User saveUser(User user);

	public User getUserById(Long userId);

	void deletUser(Long userId);
	
}