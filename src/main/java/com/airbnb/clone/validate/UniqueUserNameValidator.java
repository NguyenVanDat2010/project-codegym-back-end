package com.airbnb.clone.validate;

import com.airbnb.clone.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {
    @Autowired
    private AppUserService appUserService;

    @Override
    public void initialize(UniqueUserName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        return appUserService == null || !appUserService.existsByUserName(userName);
    }
}
