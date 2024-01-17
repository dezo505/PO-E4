package com.example.demo;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample data
        Category category1 = new Category("Electronics");
        category1.setId(1L);
        Category category2 = new Category("Clothing");
        category2.setId(2L);

        List<Category> allCategories = Arrays.asList(category1, category2);

        // Mocking repository behavior
        when(categoryRepository.findAll()).thenReturn(allCategories);
    }

    @Test
    void getAllCategories_ShouldReturnAllCategories() {
        // Act
        List<Category> resultCategories = categoryService.getAllCategories();

        // Assert
        assertThat(resultCategories).hasSize(2);
    }

}
