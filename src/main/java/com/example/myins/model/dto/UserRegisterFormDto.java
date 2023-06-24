package com.example.myins.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterFormDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 11, max = 11)
    private String phone;
    @NotBlank
    @Size(min = 10)
    private String password;
    private String confirmPassword;

}
