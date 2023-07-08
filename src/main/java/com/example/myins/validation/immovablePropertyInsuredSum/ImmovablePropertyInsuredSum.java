package com.example.myins.validation.immovablePropertyInsuredSum;

import com.example.myins.model.dto.InputHomeOfferDto;
import com.example.myins.validation.immovablePropertyInsuredSum.BuiltAreaMinInsuredSum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImmovablePropertyInsuredSum implements ConstraintValidator<BuiltAreaMinInsuredSum, InputHomeOfferDto> {
    private final static double MIN_INSURED_SUM_PER_SQUARE_METER = 800;

    @Override
    public void initialize(BuiltAreaMinInsuredSum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(InputHomeOfferDto inputHomeOfferDto,
                           ConstraintValidatorContext constraintValidatorContext) {

        double builtArea = inputHomeOfferDto.getTotalBuiltUpArea();
        double minInsSum = MIN_INSURED_SUM_PER_SQUARE_METER * builtArea;

        return minInsSum >= inputHomeOfferDto.getImmovablePropertySum();
    }
}
