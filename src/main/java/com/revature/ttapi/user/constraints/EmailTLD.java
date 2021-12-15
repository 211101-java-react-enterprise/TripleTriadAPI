package com.revature.ttapi.user.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotBlank(message = "Emails cannot be empty")
@Email(message = "Please provide a valid email address")
@Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
@Size(min = 6, max = 254, message = "Please provide a valid email address")
@ReportAsSingleViolation
@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface EmailTLD {
    String message() default "Please provide a valid email address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
