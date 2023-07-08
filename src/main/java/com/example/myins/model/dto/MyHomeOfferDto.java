package com.example.myins.model.dto;

import com.example.myins.model.enums.PaymentType;
import com.example.myins.model.enums.PolicyTitle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyHomeOfferDto {

    private Long id;
    private PolicyTitle offerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private PaymentType paymentType;
    private BigDecimal grossWrittenPremium;

}
