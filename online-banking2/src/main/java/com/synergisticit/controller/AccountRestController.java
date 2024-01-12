package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.Branch;
import com.synergisticit.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("accounts")
public class AccountRestController {
@Autowired AccountService accountService;
	
	@GetMapping(value = "all")
	public ResponseEntity<List<Account>> findAll(){
		List<Account> accounts = accountService.findAll();
		System.out.println("accountService.findAll:" + accounts);
		if(accounts.isEmpty()) {
			return new ResponseEntity<List<Account>>(accounts, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Account>>(accounts, HttpStatus.FOUND);
		}
	}
	
	@GetMapping(value = "findById")
	public ResponseEntity<Account> findById(@RequestParam long accountId){
		Account account = accountService.findById(accountId);
	
		if(account == null) {
			return new ResponseEntity<Account>(account, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Account>(account, HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
	public ResponseEntity<Account> deleteAccount(@RequestParam Long accountId) {
		HttpHeaders headers = new HttpHeaders();
		Account account = accountService.findById(accountId);
	    if (account == null) {
	        return new ResponseEntity<Account>(account, HttpStatus.NOT_FOUND);
	    } else {
	    	accountService.deleteById(accountId);
	    	headers.add("Account deleted", String.valueOf(accountId));
	        return new ResponseEntity<Account>(account, headers, HttpStatus.NO_CONTENT);
	    }
	}
	
	@PostMapping(value = "save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveAccount(@Valid @RequestBody Account account, BindingResult br){
		StringBuilder sb = new StringBuilder("");
		if(accountService.existsById(account.getAccountId()) || br.hasFieldErrors()) {
			if(br.hasFieldErrors()) {
				List<FieldError> fieldErrors = br.getFieldErrors();
				for(FieldError fieldError : fieldErrors) {
					sb = sb.append("\"" + fieldError.getField()+"\":"+fieldError.getDefaultMessage()+"\n");
				}
				System.out.println("sb: " + sb);
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
			}
			else {
				sb.append("Branch with id" + account.getAccountId() + "\"already there");
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.NOT_ACCEPTABLE);
			}
		}else {
			Account a = accountService.save(account);
			return new ResponseEntity<Account>(a, HttpStatus.CREATED);
		}
	
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateAccount(@RequestBody Account a){
		Account account = accountService.findById(a.getAccountId());
		if(account == null) {
			return new ResponseEntity<String>("No account with id"  + a.getAccountId(), HttpStatus.NOT_FOUND);
		}else {
			accountService.save(account);
			return new ResponseEntity<Account>(a, HttpStatus.OK);
		}
		
	}
}
