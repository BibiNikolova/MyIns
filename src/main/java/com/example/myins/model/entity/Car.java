package com.example.myins.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity{
    @Column(nullable = false)
    private String DKN;
    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Model model;
    @Column(nullable = false)
    private int capacity;
    @Column(nullable = false)
    private LocalDate yearOfRegistration;
    @ManyToOne
    private Client owner;
    @ManyToOne
    private Policy carPolicy;
}
