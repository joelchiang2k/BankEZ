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
		
		
		
		
	}

}
