package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serwis do obsługi operacji na danych kategorii.
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Pobiera wszystkie kategorie.
     *
     * @return Lista wszystkich kategorii.
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
