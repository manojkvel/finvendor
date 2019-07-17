package com.finvendor.common.annotations.validator;

import com.finvendor.common.annotations.ValidCategory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidCategoryValidator implements ConstraintValidator<ValidCategory, String> {
    private static final Map<String, List> availableCategories;
    static {
        availableCategories = new HashMap<>();
        availableCategories.put("subscriptionType", Arrays.asList("SMART", "SAGE"));
        availableCategories.put("paymentType", Arrays.asList("NEFT", "RTGS", "IMPS"));
    }
    private String categoryType;


    private void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public void initialize(ValidCategory constraintAnnotation) {
        this.setCategoryType(constraintAnnotation.categoryType());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> categories = ValidCategoryValidator.availableCategories.get(categoryType);
        if (categories == null || categories.isEmpty()) {
            return false;
        }

        for (String category : categories) {
            if (category.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
