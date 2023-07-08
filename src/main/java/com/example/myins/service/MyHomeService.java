package com.example.myins.service;

import com.example.myins.model.dto.InputHomeOfferDto;
import com.example.myins.repository.HomeRepo;
import org.springframework.stereotype.Service;

@Service
public class MyHomeService {

    private final HomeRepo homeRepo;

    public MyHomeService(HomeRepo homeRepo) {
        this.homeRepo = homeRepo;
    }

    private void getPrice (InputHomeOfferDto inputOffer) {


        double builtArea = inputOffer.getTotalBuiltUpArea();

    }

    private double getInsuredSumImmovable(InputHomeOfferDto homeOfferDto) {
        double builtArea = homeOfferDto.getTotalBuiltUpArea();
        return 50;
    }

//    private MyHomeOfferDto createOffer(InputHomeOfferDto inputOffer) {
//
//        Home home = Home.builder()
//                ..build();
//
//        Offer homeOffer = MyHomeOffer.builder()
//                .home().build();
//
//        return null;
//    }
}
