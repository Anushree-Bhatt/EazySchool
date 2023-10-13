package com.eazybytes.validations;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.eazybytes.annotations.PasswordValidator;

public class PasswordValidationLogic implements ConstraintValidator<PasswordValidator, String>{
	
	List<String> weakpasswords ;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) { //value = contains password entered by user
		// TODO Auto-generated method stub
		return (value!=null && !weakpasswords.contains(value));
	}
	
	@Override
	public void initialize(PasswordValidator constraintAnnotation) {
		// TODO Auto-generated method stub
	//	ConstraintValidator.super.initialize(constraintAnnotation);
		weakpasswords = List.of("12345","321","123","password");
	}

}
