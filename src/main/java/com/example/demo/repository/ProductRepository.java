package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repozytorium (interfejs) do operacji na danych produktów w bazie danych.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
