package com.example.myins.repository;

import com.example.myins.model.entity.PolicyHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyHolderRepo extends JpaRepository<PolicyHolder, Long> {

}
