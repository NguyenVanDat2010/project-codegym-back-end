package com.airbnb.clone.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = UniqueUserNameValidator.class)
@Retention(RUNTIME)
public @interface UniqueUserName {
    String message() default "Username already in use!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
