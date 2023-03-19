package com.example.myins.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/client-page")
public class ClientController {

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

}
