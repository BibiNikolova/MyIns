package com.example.myins.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class UserRegisterFormDto {
    @NotBlank   //TODO: validations
    private String username;

    private String password;

    private String email;

    private String phone;
}
