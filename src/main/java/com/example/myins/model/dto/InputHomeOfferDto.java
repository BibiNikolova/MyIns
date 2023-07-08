package com.example.myins.model.dto;

import com.example.myins.validation.immovablePropertyInsuredSum.BuiltAreaMinInsuredSum;
import com.example.myins.validation.movablePropertyInsuredSum.MovableMinInsuredSum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class InputHomeOfferDto {

    @NotBlank
    private String cityRegion;
    @Positive
    private Double totalBuiltUpArea;
    @BuiltAreaMinInsuredSum
    private Double immovablePropertySum;
    @MovableMinInsuredSum
    private Double movablePropertySum;
}
