package com.synergisticit.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.service.AccountService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@Component
public class BankTransactionValidator implements Validator {
	
	@Autowired AccountService accountService;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return BankTransaction.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		BankTransaction bankTransaction = (BankTransaction)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankTransactionId", "bankTransactionId.empty", "Bank Transaction Id should not be empty.");
		System.out.println("Bank transaction from account:" + bankTransaction.getBankTransactionFromAccount());
	
		
        if (bankTransaction.getBankTransactionFromAccount() == null) {
        		errors.rejectValue("bankTransactionFromAccount", "bankTransactionFromAccount.value", "Enter Account Id");
        }
//        else {
//        	System.out.println(bankTransaction.getBankTransactionFromAccount());
//            Account fromAccount = accountService.findById(bankTransaction.getBankTransactionFromAccount());
//            System.out.println("from account:" + fromAccount);
//            if (fromAccount == null) {
//                errors.rejectValue("bankTransactionFromAccount", "bankTransactionFromAccount.value", "From Account does not exist");
//            }
//
//        }

       
//        if (bankTransaction.getBankTransactionToAccount() == null) {
//        	errors.rejectValue("bankTransactionToAccount", "bankTransactionToAccount.value", "Enter Account Id");
//        }
//        else {
//        	Account toAccount = accountService.findById(bankTransaction.getBankTransactionToAccount());
//            if (toAccount == null) {
//            	errors.rejectValue("bankTransactionToAccount", "bankTransactionToAccount.value", "To Account does not exist");
//            }
//        
//        }
            
        
       
        
        
		if(bankTransaction.getBankTransactionType() == null) {
			errors.rejectValue("bankTransactionType", "bankTransactionType.value", "Select Bank Transaction Type.");
		}
		
		if(bankTransaction.getBankTransactionDateTime() == null) {
			errors.rejectValue("bankTransactionDateTime", "bankTransactionDateTime.value", "Select Time.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comment", "comment.empty", "Enter comment.");

		
		
		
	}

}
