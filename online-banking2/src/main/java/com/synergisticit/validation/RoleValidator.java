package com.synergisticit.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.Role;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@Component
public class RoleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Role.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Role role = (Role)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleId", "roleId.empty", "Role Id should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "Name should not be empty");	
		
	}

}
