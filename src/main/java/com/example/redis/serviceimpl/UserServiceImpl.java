package com.example.redis.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.redis.entity.User;
import com.example.redis.repository.UserRepository;
import com.example.redis.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final String REDIS_CACHE_VALUE = "USER";

	@Autowired
	UserRepository userRepository;

	@Override
	@CachePut(value = REDIS_CACHE_VALUE, key = "#user.userId")
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	@Cacheable(value = REDIS_CACHE_VALUE, key = "#userId")
	public User getUserById(Long userId) {
		System.out.println("Inside getUserById ============>>");
		Optional<User> user = userRepository.findByUserId(userId);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	@Override
	@CacheEvict(value = REDIS_CACHE_VALUE, key = "#userId")
	public void deletUser(Long userId) {
		userRepository.deleteById(userId);
	}

}