package com.example.myins.model.entity;

import com.example.myins.model.enums.PolicyTitle;
import com.example.myins.validation.immovablePropertyInsuredSum.BuiltAreaMinInsuredSum;
import com.example.myins.validation.movablePropertyInsuredSum.MovableMinInsuredSum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.apache.catalina.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "my_home_offers")
public class MyHomeOffer extends Offer {

    @Override
    public PolicyTitle getOfferName() {
        return PolicyTitle.HOME;
    }

    private String cityRegion;
    private Double immovablePropertySum;
    private Double movablePropertySum;
    @ManyToOne
    private UserEntity loggedUser;
}
