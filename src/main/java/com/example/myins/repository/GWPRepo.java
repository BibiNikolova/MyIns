package com.example.myins.repository;

import com.example.myins.model.entity.GrossWrittenPremium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GWPRepo extends JpaRepository<GrossWrittenPremium, Long> {
}
