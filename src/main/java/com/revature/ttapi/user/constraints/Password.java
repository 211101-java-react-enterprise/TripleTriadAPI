package com.revature.ttapi.user.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotBlank(message = "Passwords cannot be empty")
@Size(min = 4, max = 128, message = "Passwords must be at least {min} characters long")
@Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])(?=.*[@$!%*#?&])[A-Za-z0-9@$!%*#?&]+$", message = "Passwords must contain contain at least one number and one letter")
@ReportAsSingleViolation
@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface Password {
    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
