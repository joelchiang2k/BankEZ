package com.synergisticit.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.synergisticit.domain.BankTransaction;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@Component
public class BankTransactionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return BankTransaction.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		BankTransaction bankTransaction = (BankTransaction)target;
//		System.out.println(bankTransaction.getBankTransactionId());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankTransactionId", "bankTransactionId.empty", "Bank Transaction Id should not be empty.");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankTransactionFromAccount", "bankTransactionFromAccount.empty", "Bank Transaction From Account should not be empty.");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankTransactionToAccount", "bankTransactionToAccount.empty", "Bank Transaction To Account should not be empty.");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankTransactionAmount", "bankTransactionAmount.empty", "Bank Transaction Amount should not be empty.");
//		
		if(bankTransaction.getBankTransactionType() == null) {
			errors.rejectValue("bankTransactionType", "bankTransactionType.value", "Select Bank Transaction Type.");
		}
		
		if(bankTransaction.getBankTransactionDateTime() == null) {
			errors.rejectValue("bankTransactionDateTime", "bankTransactionDateTime.value", "Select Time.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comment", "comment.empty", "Enter comment.");

		
		
		
	}

}
