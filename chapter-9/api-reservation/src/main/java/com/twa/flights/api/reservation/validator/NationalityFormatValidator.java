package com.twa.flights.api.reservation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NationalityFormatValidator
        implements ConstraintValidator<NationalityFormatConstraint, String> {

    @Override
    public void initialize(NationalityFormatConstraint nationality) {}

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        return field != null && field.matches("[A-Z]+") && field.length() == 2;
    }
}
