package com.example.myins.validation.immovablePropertyInsuredSum;

import com.example.myins.model.dto.InputHomeOfferDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class ImmovablePropertyInsuredSum implements ConstraintValidator<BuiltAreaMinInsuredSum, InputHomeOfferDto> {
    private final static double MIN_INSURED_SUM_PER_SQUARE_METER = 800;
    private final static double NO_IMMOVABLE_INSURED = 0;

    @Override
    public void initialize(BuiltAreaMinInsuredSum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(InputHomeOfferDto inputHomeOfferDto,
                           ConstraintValidatorContext constraintValidatorContext) {

        double builtArea = inputHomeOfferDto.getTotalBuiltUpArea();
        double immovableSum = inputHomeOfferDto.getImmovablePropertySum();
        double minInsSum = MIN_INSURED_SUM_PER_SQUARE_METER * builtArea;

        return minInsSum >= immovableSum || immovableSum == NO_IMMOVABLE_INSURED;
    }
}
