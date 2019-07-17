package com.finvendor.common.annotations;

import com.finvendor.common.annotations.validator.ValidCategoryValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = {ValidCategoryValidator.class})
public @interface ValidCategory {
    String categoryType();
    String message() default "Category is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}