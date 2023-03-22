package com.example.myins.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @GetMapping("/myCar")
    public String myCar() {
        return "my-car";
    }

    @GetMapping("/myHome")
    public String myHome() {
        return "my-home";
    }

    @GetMapping("/lifeAndHealth")
    public String lifeAndHealth() {
        return "life-health";
    }



}
