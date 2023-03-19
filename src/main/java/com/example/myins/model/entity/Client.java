package com.example.myins.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client extends BaseEntity{

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
    private List<Car> cars;
    @OneToMany
    private List<Home> homes;

    @OneToMany
    private List<Policy> policies;

    private boolean electronicCommunicationAcceptance;


}
