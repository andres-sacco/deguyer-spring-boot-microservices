package com.twa.flights.api.reservation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NationalityFormatValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NationalityFormatConstraint {
    String message() default "Invalid format of the nationality. Only three letters are allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}