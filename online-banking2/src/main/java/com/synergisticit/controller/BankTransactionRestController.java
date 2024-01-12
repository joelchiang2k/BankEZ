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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.service.BankTransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("bankTransactions")
public class BankTransactionRestController {
	@Autowired BankTransactionService bankTransactionService;
	@GetMapping(value = "all")
	public ResponseEntity<List<BankTransaction>> findAll(){
		List<BankTransaction> bankTransactions = bankTransactionService.findAll();
		System.out.println("accountService.findAll:" + bankTransactions);
		if(bankTransactions.isEmpty()) {
			return new ResponseEntity<List<BankTransaction>>(bankTransactions, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<BankTransaction>>(bankTransactions, HttpStatus.FOUND);
		}
	}
	
	@GetMapping(value = "findById")
	public ResponseEntity<BankTransaction> findById(@RequestParam long bankTransactionId){
		BankTransaction bankTransaction = bankTransactionService.findById(bankTransactionId);
	
		if(bankTransaction == null) {
			return new ResponseEntity<BankTransaction>(bankTransaction, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<BankTransaction>(bankTransaction, HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
	public ResponseEntity<BankTransaction> deleteBankTransaction(@RequestParam Long bankTransactionId) {
		HttpHeaders headers = new HttpHeaders();
		BankTransaction bankTransaction = bankTransactionService.findById(bankTransactionId);
	    if (bankTransaction == null) {
	        return new ResponseEntity<BankTransaction>(bankTransaction, HttpStatus.NOT_FOUND);
	    } else {
	    	bankTransactionService.deleteById(bankTransactionId);
	    	headers.add("Bank Transaction deleted", String.valueOf(bankTransactionId));
	        return new ResponseEntity<BankTransaction>(bankTransaction, headers, HttpStatus.NO_CONTENT);
	    }
	}
	
	@PostMapping(value = "save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveBankTransaction(@Valid @RequestBody BankTransaction bankTransaction, BindingResult br){
		StringBuilder sb = new StringBuilder("");
		if(bankTransactionService.existsById(bankTransaction.getBankTransactionId()) || br.hasFieldErrors()) {
			if(br.hasFieldErrors()) {
				List<FieldError> fieldErrors = br.getFieldErrors();
				for(FieldError fieldError : fieldErrors) {
					sb = sb.append("\"" + fieldError.getField()+"\":"+fieldError.getDefaultMessage()+"\n");
				}
				System.out.println("sb: " + sb);
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
			}
			else {
				sb.append("Bank Transaction with id" + bankTransaction.getBankTransactionId() + "\"already there");
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.NOT_ACCEPTABLE);
			}
		}else {
			BankTransaction b = bankTransactionService.save(bankTransaction);
			return new ResponseEntity<BankTransaction>(b, HttpStatus.CREATED);
		}
	
	}
}
