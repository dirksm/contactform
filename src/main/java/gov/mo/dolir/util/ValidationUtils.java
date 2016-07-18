package gov.mo.dolir.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;

public class ValidationUtils extends org.springframework.validation.ValidationUtils {

	/**
	 * Reject the given field with the given error code, error arguments
	 * and default message if the first value is different from the second value.
	 * <p>The object whose field is being validated does not need to be passed
	 * in because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * @param errors the <code>Errors</code> instance to register errors on
	 * @param field1 the field name to check
	 * @param field2 the field name to check against field1
	 * @param errorCode the error code, interpretable as message key
	 */
	public static void rejectIfDifferent(Errors errors, String field1, String field2, String errorCode) {
		rejectIfDifferent(errors, field1, field2, errorCode, null, null);
	}
	
	/**
	 * Reject the given field with the given error code, error arguments
	 * and default message if the first value is different from the second value.
	 * <p>The object whose field is being validated does not need to be passed
	 * in because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * @param errors the <code>Errors</code> instance to register errors on
	 * @param field1 the field name to check
	 * @param field2 the field name to check against field1
	 * @param errorCode the error code, interpretable as message key
	 * @param defaultMessage fallback default message
	 */
	public static void rejectIfDifferent(Errors errors, String field1, String field2, String errorCode, String defaultMessage) {
		rejectIfDifferent(errors, field1, field2, errorCode, null, defaultMessage);
	}

	/**
	 * Reject the given field with the given error code, error arguments
	 * and default message if the first value is different from the second value.
	 * <p>The object whose field is being validated does not need to be passed
	 * in because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * @param errors the <code>Errors</code> instance to register errors on
	 * @param field1 the field name to check
	 * @param field2 the field name to check against field1
	 * @param errorCode the error code, interpretable as message key
	 * @param errorArgs the error arguments, for argument binding via MessageFormat
	 * (can be <code>null</code>)
	 */
	public static void rejectIfDifferent(Errors errors, String field1, String field2, String errorCode, Object[] errorArgs) {
		rejectIfDifferent(errors, field1, field2, errorCode, errorArgs, null);
	}

	/**
	 * Reject the given field with the given error code, error arguments
	 * and default message if the first value is different from the second value.
	 * <p>The object whose field is being validated does not need to be passed
	 * in because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * @param errors the <code>Errors</code> instance to register errors on
	 * @param field1 the field name to check
	 * @param field2 the field name to check against field1
	 * @param errorCode the error code, interpretable as message key
	 * @param errorArgs the error arguments, for argument binding via MessageFormat
	 * (can be <code>null</code>)
	 * @param defaultMessage fallback default message
	 */
	public static void rejectIfDifferent(Errors errors, String field1, String field2, String errorCode, Object[] errorArgs, String defaultMessage) {
		Assert.notNull(errors, "Errors object must not be null");
		Object value1 = errors.getFieldValue(field1);
		Object value2 = errors.getFieldValue(field2);
		try {
			String str1 = String.valueOf(value1);
			String str2 = String.valueOf(value2);
			if (!str1.equals(str2)) {
				errors.rejectValue(field1, errorCode, errorArgs, defaultMessage);
				errors.rejectValue(field2, errorCode, errorArgs, defaultMessage);
			}
		} catch (Exception e) {
			errors.rejectValue(field1, errorCode, errorArgs, defaultMessage);
			errors.rejectValue(field2, errorCode, errorArgs, defaultMessage);
		}
	}
	
	/**
	 * Reject the given field with the given error code, error arguments
	 * and default message if the value is not an email address.
	 * <p>The object whose field is being validated does not need to be passed
	 * in because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * @param errors the <code>Errors</code> instance to register errors on
	 * @param field the field name to check
	 * @param errorCode the error code, interpretable as message key
	 */
	public static void rejectIfNotEmail(Errors errors, String field, String errorCode) {
		rejectIfNotEmail(errors, field, errorCode, null, null);
	}

	/**
	 * Reject the given field with the given error code, error arguments
	 * and default message if the value is not an email address.
	 * <p>The object whose field is being validated does not need to be passed
	 * in because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * @param errors the <code>Errors</code> instance to register errors on
	 * @param field1 the field name to check
	 * @param field2 the field name to check against field1
	 * @param errorCode the error code, interpretable as message key
	 * @param defaultMessage fallback default message
	 */
	public static void rejectIfNotEmail(Errors errors, String field, String errorCode, String defaultMessage) {
		rejectIfNotEmail(errors, field, errorCode, null, defaultMessage);
	}
	
	/**
	 * Reject the given field with the given error code, error arguments
	 * and default message if the value is not an email address.
	 * <p>The object whose field is being validated does not need to be passed
	 * in because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * @param errors the <code>Errors</code> instance to register errors on
	 * @param field1 the field name to check
	 * @param field2 the field name to check against field1
	 * @param errorCode the error code, interpretable as message key
	 * @param errorArgs the error arguments, for argument binding via MessageFormat
	 * (can be <code>null</code>)
	 */
	public static void rejectIfNotEmail(Errors errors, String field, String errorCode, Object[] errorArgs) {
		rejectIfNotEmail(errors, field, errorCode, errorArgs, null);
	}

	/**
	 * Reject the given field with the given error code, error arguments
	 * and default message if the value is not an email address.
	 * This method checks to see that the email isn't empty.
	 * <p>The object whose field is being validated does not need to be passed
	 * in because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * @param errors the <code>Errors</code> instance to register errors on
	 * @param field the field name to check
	 * @param errorCode the error code, interpretable as message key
	 * @param errorArgs the error arguments, for argument binding via MessageFormat
	 * (can be <code>null</code>)
	 * @param defaultMessage fallback default message
	 */
	public static void rejectIfNotEmail(Errors errors, String field, String errorCode, Object[] errorArgs, String defaultMessage) {
		Pattern pattern = Pattern.compile("^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);
		try {
			String value = String.valueOf(errors.getFieldValue(field));
			Matcher match = pattern.matcher(value);
			if (StringUtils.isNotBlank(value) && !match.matches()) {
				errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
			}
		} catch (Exception e) {
			errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
		}
	
	}
	
	/**
	 * Reject the given field with the given error code, error arguments
	 * and default message if the value is empty or zero.
	 * <p>An 'empty' value in this context means either <code>null</code> or
	 * the empty string "". 
	 * <p>The object whose field is being validated does not need to be passed
	 * in because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * @param errors the <code>Errors</code> instance to register errors on
	 * @param field the field name to check
	 * @param errorCode the error code, interpretable as message key
	 */
	public static void rejectIfZero(Errors errors, String field, String errorCode) {
		rejectIfZero(errors, field, errorCode, null, null);
	}
	
	/**
	 * Reject the given field with the given error code, error arguments
	 * and default message if the value is empty or zero.
	 * <p>An 'empty' value in this context means either <code>null</code> or
	 * the empty string "". 
	 * <p>The object whose field is being validated does not need to be passed
	 * in because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * @param errors the <code>Errors</code> instance to register errors on
	 * @param field the field name to check
	 * @param errorCode the error code, interpretable as message key
	 * @param defaultMessage fallback default message
	 */
	public static void rejectIfZero(Errors errors, String field, String errorCode, String defaultMessage) {
		rejectIfZero(errors, field, errorCode, null, defaultMessage);
	}
	
	/**
	 * Reject the given field with the given error code, error arguments
	 * and default message if the value is empty or zero.
	 * <p>An 'empty' value in this context means either <code>null</code> or
	 * the empty string "". 
	 * <p>The object whose field is being validated does not need to be passed
	 * in because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * @param errors the <code>Errors</code> instance to register errors on
	 * @param field the field name to check
	 * @param errorCode the error code, interpretable as message key
	 * @param errorArgs the error arguments, for argument binding via MessageFormat
	 * (can be <code>null</code>)
	 */
	public static void rejectIfZero(Errors errors, String field, String errorCode, Object[] errorArgs) {
		rejectIfZero(errors, field, errorCode, errorArgs, null);
	}
	
	/**
	 * Reject the given field with the given error code, error arguments
	 * and default message if the value is empty or zero.
	 * <p>An 'empty' value in this context means either <code>null</code> or
	 * the empty string "". 
	 * <p>The object whose field is being validated does not need to be passed
	 * in because the {@link Errors} instance can resolve field values by itself
	 * (it will usually hold an internal reference to the target object).
	 * @param errors the <code>Errors</code> instance to register errors on
	 * @param field the field name to check
	 * @param errorCode the error code, interpretable as message key
	 * @param errorArgs the error arguments, for argument binding via MessageFormat
	 * (can be <code>null</code>)
	 * @param defaultMessage fallback default message
	 */
	public static void rejectIfZero(
			Errors errors, String field, String errorCode, Object[] errorArgs, String defaultMessage) {
		Assert.notNull(errors, "Errors object must not be null");
		Object value = errors.getFieldValue(field);
		try {
			int intVal = Integer.parseInt(value.toString());
			if (intVal == 0) {
				errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
			}
		} catch (Exception e) {
			errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
		}
	}
	
}
