package com.example.myins.repository;

import com.example.myins.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepo extends JpaRepository<Model, Long> {

}
