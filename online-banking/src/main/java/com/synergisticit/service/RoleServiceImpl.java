package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.repository.RoleRepository;
import com.synergisticit.domain.Role;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired RoleRepository roleRepository ;

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role findById(Long roleId) {
		Optional<Role> optRole = roleRepository.findById(roleId);
		if(optRole.isPresent()) {
			return optRole.get();
		}
		return null;
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

}
