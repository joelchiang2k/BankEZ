package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Branch;
import com.synergisticit.domain.Role;
import com.synergisticit.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {
	
	@Autowired BranchRepository branchRepository ;
	@Override
	public Branch save(Branch branch) {
		// TODO Auto-generated method stub
		return branchRepository.save(branch);
	}

	@Override
	public Branch findById(Long branchId) {
		Optional<Branch> optBranch = branchRepository.findById(branchId);
		if(optBranch.isPresent()) {
			return optBranch.get();
		}
		return null;
	}
	
	@Override
	public boolean deleteById(Long branchId) {
	    try {
	        branchRepository.deleteById(branchId);
	        return true; 
	    } catch (EmptyResultDataAccessException e) {
	        return false; 
	    }
	}


	@Override
	public List<Branch> findAll() {
		// TODO Auto-generated method stub
		return branchRepository.findAll();
	}
	
	@Override
	public boolean existsById(Long branchId) {
		return branchRepository.existsById(branchId);
	}

}
