package com.example.myins.service;

import com.example.myins.model.dto.InputHomeOfferDto;
import com.example.myins.model.dto.MyHomeOfferDto;
import com.example.myins.model.entity.Company;
import com.example.myins.model.entity.GrossWrittenPremium;
import com.example.myins.model.entity.MyHomeOffer;
import com.example.myins.model.enums.PaymentType;
import com.example.myins.model.enums.PolicyTitle;
import com.example.myins.repository.CompanyRepo;
import com.example.myins.repository.MyHomeOfferRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyHomeService {
    private final MyHomeOfferRepo myHomeOfferRepo;
    private final CompanyRepo companyRepo;

    public MyHomeService(MyHomeOfferRepo myHomeOfferRepo, CompanyRepo companyRepo) {
        this.myHomeOfferRepo = myHomeOfferRepo;
        this.companyRepo = companyRepo;
    }

    public MyHomeOfferDto createOffer(InputHomeOfferDto inputOffer) {

        MyHomeOffer offer = new MyHomeOffer();
        offer.setOfferName(PolicyTitle.HOME);
        offer.setCityRegion(inputOffer.getCityRegion());
        offer.setStartDate(LocalDate.now().plusDays(1));
        offer.setEndDate(LocalDate.now().plusYears(1));
        offer.setImmovablePropertySum(inputOffer.getImmovablePropertySum());
        offer.setMovablePropertySum(inputOffer.getMovablePropertySum());
        offer.setPremiums(getAllPrices(inputOffer));
        offer.setPaymentType(PaymentType.ANNUAL);

//        if(isAuthenticated) {//TODO: save as user is logged
//
//            offer.setLoggedUser(LoggedUser logged);
//            myHomeOfferRepo.save(offer);
//        }


        return MyHomeOfferDto.builder()
                .offerId(offer.getId())
                .offerName(offer.getOfferName())
                .cityRegion(offer.getCityRegion())
                .startDate(offer.getStartDate())
                .endDate(offer.getEndDate())
                .immovablePropertySum(offer.getImmovablePropertySum())
                .movablePropertySum(offer.getMovablePropertySum())
                .paymentType(offer.getPaymentType())
                .premiums(offer.getPremiums())
                .build();
    }

    private Company getCompanyByName(String name) {

        return companyRepo.findByName(name).orElseThrow();
    }

    private List<GrossWrittenPremium>  getAllPrices (InputHomeOfferDto homeOfferDto) {

        List<GrossWrittenPremium> allIn = new ArrayList<>();

        List<Company> companies = companyRepo.findAll();
        companies.forEach(company -> {

            double immovable = homeOfferDto.getImmovablePropertySum();
            double movable = homeOfferDto.getMovablePropertySum();
            double additional = 0;

            String name = company.getName();
            switch (name) {
                case "Allianz" -> {
                    immovable *= 0.07;
                    movable *= 0.024;
                    additional = (27 + 15 + 4);
                }
                case "Uniqa" -> {
                    immovable *= 0.071;
                    movable *= 0.0248;
                    additional = (27 + 15 + 3);
                }
                case "DZI" -> {
                    immovable *= 0.074;
                    movable *= 0.0245;
                    additional = (27 + 15 + 2);
                }
                default -> {
                }

            }
            double sum = immovable + movable + additional;
            BigDecimal price = BigDecimal.valueOf(sum);

            GrossWrittenPremium gwp = GrossWrittenPremium.builder()
                    .company(getCompanyByName(name))
                    .sum(price)
                    .build();

            allIn.add(gwp);

        });

        return allIn;

    }

}
