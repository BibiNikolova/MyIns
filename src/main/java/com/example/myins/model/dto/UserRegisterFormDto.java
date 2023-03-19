package com.example.myins.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterFormDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 9, max = 9)
    private String phone;
    @NotBlank
    @Size(min = 5)
    private String password;

}
