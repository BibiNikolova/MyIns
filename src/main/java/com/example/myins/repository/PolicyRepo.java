package com.example.myins.repository;

import com.example.myins.model.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepo extends JpaRepository<Policy, Long> {

}
