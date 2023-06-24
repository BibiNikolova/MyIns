package com.example.myins.model.entity;

import com.example.myins.model.enums.HomeType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Table(name = "homes")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Double totalBuiltUpArea;

    @OneToOne
    private Address address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HomeType homeType;

    @ManyToOne
    private Client owner;

    @OneToOne
    private Policy homePolicy;
}
