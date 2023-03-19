package com.example.myins.model.entity;

import com.example.myins.model.enums.BrandName;
import com.example.myins.model.enums.ModelName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private BrandName brandName;

//    private Model model;

}
