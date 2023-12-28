package com.synergisticit.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.Customer;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@Component
public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Customer customer = (Customer)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerId", "customerId.empty", "Customer Id should not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerName", "customerName.empty", "Customer Name should not be empty.");
		
		if(customer.getGender() == null) {
			errors.rejectValue("gender", "gender.value", "Select Gender.");
		}
		
		if(customer.getCustomerDOB() == null) {
			errors.rejectValue("customerDOB", "customerDOB.value", "Select Date.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerMobileNo", "customerMobileNo.empty", "Mobile No should not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.addressLine1", "customerAddress.addressLine1.empty", "Address Line 1 should not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.addressLine2", "customerAddress.addressLine2.empty", "Address Line 2 should not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.city", "customerAddress.city", "City should not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.state", "customerAddress.state", "State should not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.country", "customerAddress.country", "Country should not be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.zipcode", "customerAddress.zipcode", "Zipcode should not be empty.");
		
		
	}

}
