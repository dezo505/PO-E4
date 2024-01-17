package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repozytorium (interfejs) do operacji na danych kategorii w bazie danych.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
