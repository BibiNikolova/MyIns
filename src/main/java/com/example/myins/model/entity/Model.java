package com.example.myins.model.entity;

import com.example.myins.model.enums.ModelName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "models")
public class Model extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private ModelName modelName;

}
