package com.example.myins.controller;

import com.example.myins.service.MyHomeService;
import org.springframework.stereotype.Controller;

@Controller
public class MyHomeOfferController {

    private final MyHomeService myHomeService;

    public MyHomeOfferController(MyHomeService myHomeService) {
        this.myHomeService = myHomeService;
    }
}


