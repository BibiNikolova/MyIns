package com.example.myins.controller;

import com.example.myins.model.dto.UserRegisterFormDto;
import com.example.myins.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users/login")
public class UserRegisterController {

    private final AuthService authService;

    public UserRegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterFormDto userRegisterFormDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterFormDto", userRegisterFormDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registrationDTO", bindingResult);

            return "redirect:/register";
        }

        authService.registerUser(userRegisterFormDto);

        return "redirect:/users/login";
    }

    @ModelAttribute(name = "userRegisterFormDto")
    public UserRegisterFormDto initUserRegisterFormDto() {
        return new UserRegisterFormDto();
    }
}
