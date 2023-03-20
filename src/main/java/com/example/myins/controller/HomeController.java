package com.example.myins.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/travel")
    public String travel () {
        return "travel";
    }

}
