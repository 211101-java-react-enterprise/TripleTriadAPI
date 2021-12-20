package com.revature.ttapi.user.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotNull(message = "Usernames cannot be null")
@Size(min = 2, max = 32, message = "Usernames must be between {min} and {max} characters long")
@Pattern(regexp = "^[a-z][a-z0-9_-]", message = "Usernames must start with a letter, and only contain alphanumeric characters, underscores, and dashes.")
@ReportAsSingleViolation
@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface Username {
    String message() default "Invalid Username: '${validatedValue}'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
