package com.example.myins.model.entity;

import com.example.myins.model.enums.HomeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "homes")
public class Home extends BaseEntity{
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
