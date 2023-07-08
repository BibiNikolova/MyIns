package com.example.myins.validation.movablePropertyInsuredSum;

import com.example.myins.model.dto.InputHomeOfferDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MovablePropertyInsuredSum implements ConstraintValidator<MovableMinInsuredSum, InputHomeOfferDto> {
    private final static double MIN_INSURED_SUM_FOR_MOVABLE = 0.2;

    @Override
    public void initialize(MovableMinInsuredSum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(InputHomeOfferDto inputHomeOfferDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        double immovableSum = inputHomeOfferDto.getImmovablePropertySum();
        double minMovable = immovableSum * MIN_INSURED_SUM_FOR_MOVABLE;

        return minMovable >= inputHomeOfferDto.getMovablePropertySum();
    }
}
