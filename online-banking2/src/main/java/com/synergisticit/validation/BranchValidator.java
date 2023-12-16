package com.synergisticit.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Branch;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

@Component
public class BranchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Branch.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Branch branch = (Branch)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchId", "branchId.empty", "branchId should not be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchName", "branchName.empty", "BranchName should not be empty");
		
		
		if(branch.getBranchAddress().getAddressLine1().isBlank() || branch.getBranchAddress().getAddressLine1().isEmpty()) {
			System.out.println("works");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchAddress.addressLine1", "Branch.branchAddress.addressLine1.empty", "Address Line 1 should not be empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchAddress.addressLine2", "Branch.branchAddress.addressLine2.empty", "Address Line 2 should not be empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchAddress.city", "Branch.branchAddress.city.empty", "City should not be empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchAddress.state", "Branch.branchAddress.state.empty", "State should not be empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchAddress.country", "Branch.branchAddress.country.empty", "Country should not be empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchAddress.zipcode", "Branch.branchAddress.zipcode.empty", "ZipCode should not be empty");
		
		}
		
		
		
	}

}
