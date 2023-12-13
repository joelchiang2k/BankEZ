package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.repository.UserRepository;
import com.synergisticit.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired UserRepository userRepository;
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findById(Long userId) {
		Optional<User> optUser = userRepository.findById(userId);
		if(optUser.isPresent()) {
			return optUser.get();
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public User updateById(Long userId) { // This method used is used only to fill the form, it can also be managed by method findById(Long userId). 
		Optional<User> optUser = userRepository.findById(userId);
		if(optUser.isPresent())
			return optUser.get();
		return null;
	}

}
