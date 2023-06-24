package com.example.myins.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
