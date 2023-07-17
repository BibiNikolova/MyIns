package com.example.myins.controller;

import com.example.myins.model.dto.InputTravelOfferDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TravelController {

    @GetMapping("/travel")
    public String travel() {
        return "travel";
    }

    @PostMapping("/travel")
    public String songs(@Valid InputTravelOfferDto inputTravelOfferDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("inputTravelOfferDto", inputTravelOfferDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.inputTravelOfferDto", bindingResult);

            return "redirect:/travel";
        }
        return "redirect:/travel/offer";
    }

    @ModelAttribute(name = "inputTravelOfferDto")
    public InputTravelOfferDto initInputTravelOfferDto() {
        return new InputTravelOfferDto();
    }
}


