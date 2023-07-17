package com.example.myins.model.dto;

import com.example.myins.model.entity.GrossWrittenPremium;
import com.example.myins.model.enums.PaymentType;
import com.example.myins.model.enums.PolicyTitle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyHomeOfferDto {

    private Long offerId;
    private PolicyTitle offerName;
    private String cityRegion;
    private Double immovablePropertySum;
    private Double movablePropertySum;
    private LocalDate startDate;
    private LocalDate endDate;
    private PaymentType paymentType;
    private List<GrossWrittenPremium> premiums;

}
