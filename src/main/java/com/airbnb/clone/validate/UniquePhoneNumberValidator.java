package com.airbnb.clone.validate;

import com.airbnb.clone.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {
    @Autowired
    private AppUserService appUserService;

    @Override
    public void initialize(UniquePhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return appUserService == null || !appUserService.existsByPhoneNumber(phoneNumber);
    }
}
