package com.example.myins.validation.immovablePropertyInsuredSum;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ImmovablePropertyInsuredSum.class)
public @interface BuiltAreaMinInsuredSum {

    String message() default "Invalid min sum";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
