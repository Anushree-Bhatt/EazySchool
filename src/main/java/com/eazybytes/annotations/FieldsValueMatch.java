package com.eazybytes.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.eazybytes.validations.FieldsValueMatchLogic;


@Target(ElementType.TYPE) //TYPE -> class level, enum level or interface level;
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsValueMatchLogic.class)
public @interface FieldsValueMatch {
	
	//define all parameters
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String field();
	String fieldMatch();
	
	String message() default "";
	
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface List{
		FieldsValueMatch[] values();
	}
}
