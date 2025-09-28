package com.example.Myfirstproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.Myfirstproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// This interface automatically gives you CRUD methods
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAll(Pageable pageable);
}

