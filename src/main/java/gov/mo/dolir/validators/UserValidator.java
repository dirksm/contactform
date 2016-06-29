package gov.mo.dolir.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import gov.mo.dolir.models.UserModel;
import gov.mo.dolir.util.ValidationUtils;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserModel.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserModel bean = (UserModel)target;
		validateCommonFields(target, errors);
	}

	public void validateCommonFields(Object target, Errors errors) {
		UserModel bean = (UserModel)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required", "First Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required", "Last Name is required.");
		if (StringUtils.isNotBlank(bean.getEmail())) {
			ValidationUtils.rejectIfNotEmail(errors, "email", "required", "Email address is not valid.");
		}
	}
	
	public void validateCreation(Object target, Errors errors) {
		UserModel bean = (UserModel)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required", "Username is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "Password is required");
		ValidationUtils.rejectIfDifferent(errors, "password", "password2", "passwords.noMatch", "Passwords do not match.");
		if (StringUtils.isNotBlank(bean.getEmail())) {
			ValidationUtils.rejectIfNotEmail(errors, "email", "required", "Email address is not valid.");
		}
	}
	

	public void validatePasswordChange(Object target, Errors errors) {
		UserModel user = (UserModel)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "required", "Password is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "Password is required");
		ValidationUtils.rejectIfDifferent(errors, "password", "password2", "passwords.noMatch", "Passwords do not match.");
	}
	
}
