package com.airbnb.clone.validate;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RUNTIME)
public @interface UniqueEmail {
    String message() default "Email cannot be duplicated!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
