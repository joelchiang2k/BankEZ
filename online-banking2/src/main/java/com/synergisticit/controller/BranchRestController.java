package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Branch;
import com.synergisticit.service.BranchService;

@RestController
@RequestMapping("branches")
public class BranchRestController {
	@Autowired BranchService branchService;
	
	@GetMapping(value = "all")
	public ResponseEntity<List<Branch>> findAll(){
		List<Branch> branches = branchService.findAll();
		System.out.println("branchService.findAll:" + branches);
		if(branches.isEmpty()) {
			return new ResponseEntity<List<Branch>>(branches, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Branch>>(branches, HttpStatus.FOUND);
		}
	}
	
//	@GetMapping(value="byId", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Branch> findById(@RequestParam Long branchId){
//		Branch branch = branchService.findById(branchId);
//		if(branch == null) {
//			return new ResponseEntity<Branch>(branch, HttpStatus.NOT_FOUND);
//		} else {
//			return new ResponseEntity<Branch>(branch, HttpStatus.FOUND);
//		}
//	}
}
