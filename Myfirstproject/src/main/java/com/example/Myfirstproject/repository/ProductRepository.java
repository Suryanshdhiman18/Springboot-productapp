package com.example.Myfirstproject.repository;

import com.example.Myfirstproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// This interface automatically gives you CRUD methods
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

