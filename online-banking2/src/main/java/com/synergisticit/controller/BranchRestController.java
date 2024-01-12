package com.synergisticit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Branch;
import com.synergisticit.service.BranchService;

import jakarta.validation.Valid;

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
	
	@GetMapping(value = "findById")
	public ResponseEntity<Branch> findById(@RequestParam long branchId){
		Branch branch = branchService.findById(branchId);
	
		if(branch == null) {
			return new ResponseEntity<Branch>(branch, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Branch>(branch, HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
	public ResponseEntity<Branch> deleteBranch(@RequestParam Long branchId) {
		HttpHeaders headers = new HttpHeaders();
		Branch branch = branchService.findById(branchId);
	    if (branch == null) {
	        return new ResponseEntity<Branch>(branch, HttpStatus.NOT_FOUND);
	    } else {
	    	branchService.deleteById(branchId);
	    	headers.add("Branch deleted", String.valueOf(branchId));
	        return new ResponseEntity<Branch>(branch, headers, HttpStatus.NO_CONTENT);
	    }
	}
	
	@PostMapping(value = "save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveBranch(@Valid @RequestBody Branch branch, BindingResult br){
		StringBuilder sb = new StringBuilder("");
		if(branchService.existsById(branch.getBranchId()) || br.hasFieldErrors()) {
			if(br.hasFieldErrors()) {
				List<FieldError> fieldErrors = br.getFieldErrors();
				for(FieldError fieldError : fieldErrors) {
					sb = sb.append("\"" + fieldError.getField()+"\":"+fieldError.getDefaultMessage()+"\n");
				}
				System.out.println("sb: " + sb);
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
			}
			else {
				sb.append("Branch with id" + branch.getBranchId() + "\"already there");
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.NOT_ACCEPTABLE);
			}
		}else {
			Branch b = branchService.save(branch);
			return new ResponseEntity<Branch>(b, HttpStatus.CREATED);
		}
	
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateBranch(@RequestBody Branch b){
		Branch branch = branchService.findById(b.getBranchId());
		if(branch == null) {
			return new ResponseEntity<String>("No branch with id"  + b.getBranchId(), HttpStatus.NOT_FOUND);
		}else {
			branchService.save(branch);
			return new ResponseEntity<Branch>(b, HttpStatus.OK);
		}
		
	}
	/*
	{
    "branchId": 10,
    "branchName": "Chase",
    "branchAddress": {
        "addressLine1": "line1",
        "addressLine2": "line2",
        "city": "City1",
        "state": "California",
        "country": "United States",
        "zipcode": "44444"
    	}
    }
    */
}


