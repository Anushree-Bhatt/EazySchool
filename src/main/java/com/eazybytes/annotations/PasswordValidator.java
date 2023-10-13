package com.eazybytes.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.eazybytes.validations.PasswordValidationLogic;

@Constraint(validatedBy = PasswordValidationLogic.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PasswordValidator {

	//define all parameters
	
		Class<?>[] groups() default {};
		Class<? extends Payload>[] payload() default {};
		
		String message() default "password is weak!!";
}
