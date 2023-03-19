package com.example.myins.repository;

import com.example.myins.model.entity.Brand;
import com.example.myins.model.enums.BrandName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {

    Brand findByBrandName(BrandName brandName);

}

