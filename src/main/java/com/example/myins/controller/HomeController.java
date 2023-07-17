package com.example.myins.controller;

import com.example.myins.model.dto.InputHomeOfferDto;
import com.example.myins.service.MyHomeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private final MyHomeService myHomeService;

    public HomeController(MyHomeService myHomeService) {
        this.myHomeService = myHomeService;
    }

    @GetMapping("/myCar")
    public String myCar() {
        return "my-car";
    }

    @GetMapping("/myHome")
    public String myHome() {
        return "my-home";
    }

    @PostMapping("/myHome")
    public String homeOffer(@Valid InputHomeOfferDto inputHomeOfferDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("inputHomeOfferDto", inputHomeOfferDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.inputHomeOfferDto", bindingResult);

            return "redirect:/myHome";
        }

        myHomeService.createOffer(inputHomeOfferDto);

        return "redirect:/myHome/offer";
    }

    @GetMapping("/lifeAndHealth")
    public String lifeAndHealth() {
        return "life-health";
    }


    @ModelAttribute(name = "inputHomeOfferDto")
    public InputHomeOfferDto initInputHomeOfferDto() {
        return new InputHomeOfferDto();
    }
}
