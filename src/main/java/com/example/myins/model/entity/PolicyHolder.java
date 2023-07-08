package com.example.myins.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "policy_holders")
//company as policyholder is not available yet
public class PolicyHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String surName;
    @Column(nullable = false)
    private String familyName;

    @Column(nullable = false)
    private int PIN;

    @OneToOne
    private Address address;

    @Column
    private int phone;

    @Column
    private String email;

    @OneToMany
    private Set<Car> cars;
    @OneToMany
    private Set<Home> homes;

    @OneToMany
    private List<Policy> policies;

}
