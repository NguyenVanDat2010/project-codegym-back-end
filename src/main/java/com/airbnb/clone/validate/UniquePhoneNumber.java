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
@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RUNTIME)
public @interface UniquePhoneNumber {
//    String pattern() default "";
    String message() default "Phone number already in use!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
