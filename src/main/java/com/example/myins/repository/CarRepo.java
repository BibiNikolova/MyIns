package com.example.myins.repository;

import com.example.myins.model.entity.Address;
import com.example.myins.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

}

