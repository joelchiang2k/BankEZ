package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Branch;



public interface BranchService {
	 public Branch save(Branch branch);
     public Branch findById(Long branchId);
     public List<Branch> findAll();
     public boolean deleteById(Long branchId);
     boolean existsById(Long branchId);
}
