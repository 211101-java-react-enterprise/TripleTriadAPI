package com.revature.ttapi.user.validators.password;

import com.revature.ttapi.common.dtos.PasswordForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, PasswordForm> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(PasswordForm form, ConstraintValidatorContext context) {
        return form.getPassword().equals(form.getMatchingPassword());
    }
}
