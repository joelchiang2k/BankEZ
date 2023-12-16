package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.User;

public interface UserService {
	public User save(User user);
	public User findById(Long userId);
	public List<User> findAll();
	public void deleteById(Long userId);
	
	public User updateById(Long userId);
	
}
