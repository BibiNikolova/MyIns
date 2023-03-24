package com.example.myins.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public UserRegisterFormDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserRegisterFormDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterFormDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterFormDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
