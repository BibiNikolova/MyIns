package com.example.myins.repository;

import com.example.myins.model.entity.MyHomeOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyHomeOfferRepo extends JpaRepository<MyHomeOffer, Long> {


}
