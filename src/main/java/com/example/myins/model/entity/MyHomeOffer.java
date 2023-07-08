package com.example.myins.model.entity;

import com.example.myins.model.enums.PolicyTitle;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MyHomeOffer extends Offer{

    @Override
    public PolicyTitle getOfferName() {
        return PolicyTitle.HOME;
    }

    private Home home;
}
