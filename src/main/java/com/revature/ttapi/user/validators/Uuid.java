package com.revature.ttapi.user.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotNull(message = "UUIDs cannot be null")
@Size(min = 36, max = 36, message = "UUIDs must be exactly {max} characters long")
@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "UUIDs must be in the format 8-4-4-4-12")
@ReportAsSingleViolation
@Target({METHOD, FIELD, PARAMETER})
@Constraint(validatedBy = {})
@Retention(RUNTIME)
public @interface Uuid {
    String message() default "Invalid UUID: '${validatedValue}'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
