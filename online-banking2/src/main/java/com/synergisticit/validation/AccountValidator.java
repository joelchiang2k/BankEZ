package com.synergisticit.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Account;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@Component
public class AccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		Account account = (Account)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountId", "accountId.empty", "accountId should not be empty");
		
		if(account.getAccountType() == null) {
			errors.rejectValue("accountType", "accountType.value", "Select Account Type.");
		}
		
		if(account.getAccountDateOpened() == null) {
			errors.rejectValue("accountDateOpened", "accountDateOpened.value", "You did not select date opened.");
		}
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountHolder", "accountHolder.empty", "accountHolder should not be empty");
		if(account.getAccountCustomer().getCustomerId() == null) {
			errors.rejectValue("accountCustomer.customerId", "accountCustomer.customerId.value", "Enter Customer Id.");
		}
		
		

		
		
		
		
		
		
		
	}

}
