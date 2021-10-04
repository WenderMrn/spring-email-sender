package com.api.sendinblue.sender.validation.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.api.sendinblue.sender.utils.Utils;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {

	@Override
	public void initialize(EmailConstraint email) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext ctx) {
		return Utils.isValidEmail(email);
	}

}