package com.example.myins.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @OneToOne
    private Policy carPolicy;
}
