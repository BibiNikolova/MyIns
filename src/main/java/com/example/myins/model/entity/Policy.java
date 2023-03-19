package com.example.myins.model.entity;

import com.example.myins.model.enums.PaymentType;
import com.example.myins.model.enums.PolicyTitle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "policies")
public class Policy extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PolicyTitle policyTitle;

    @Column(nullable = false)
    private String policyNumber;

    @ManyToOne
    private Client insured;

    @Column(nullable = false)
    private Long insuredSum;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(nullable = false)
    private BigDecimal grossWrittenPremium;

}
