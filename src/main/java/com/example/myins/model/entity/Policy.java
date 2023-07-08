package com.example.myins.model.entity;

import com.example.myins.model.enums.PaymentType;
import com.example.myins.model.enums.PolicyTitle;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity(name = "policies")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PolicyTitle policyTitle;

    @Column(nullable = false)
    private String policyNumber;

    @ManyToOne
    private PolicyHolder policyHolder;

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
