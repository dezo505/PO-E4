package com.example.demo;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample data
        Category category1 = new Category("Electronics");
        category1.setId(1L);
        Category category2 = new Category("Fruits");
        category2.setId(2L);

        Product product1 = new Product("Laptop", "Powerful laptop", 1500.0, 10, category1);
        product1.setId(1L);
        Product product2 = new Product("Smartphone", "High-end smartphone", 800.0, 5, category1);
        product2.setId(2L);
        Product product3 = new Product("Watermelon", "Juicy watermelon", 100.0, 0, category2);
        product3.setId(3L);
        Product product4 = new Product("Laptop 2", "Regular laptop", 1000.0, 20, category1);
        product4.setId(4L);

        List<Product> allProducts = List.of(product1, product2, product3, product4);

        // Mocking repository behavior
        when(productRepository.findAll()).thenReturn(allProducts);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(10L)).thenReturn(Optional.empty());
    }

    @Test
    void filterProducts_WithValidInput_ShouldReturnFilteredProducts() {
        // Act
        List<Product> filteredProducts = productService.filterProducts("phone", BigDecimal.valueOf(500.0),
                BigDecimal.valueOf(1000.0), 1L, "available");

        // Assert
        assertThat(filteredProducts).hasSize(1);
        assertThat(filteredProducts.get(0).getName()).isEqualTo("Smartphone");
    }

    @Test
    void filterProducts_WithNullInput_ShouldReturnAllProducts() {
        // Act
        List<Product> filteredProducts = productService.filterProducts(null, null, null, null, null);

        // Assert
        assertThat(filteredProducts).hasSize(4);
    }

    @Test
    void filterProducts_WithSearchInput_ShouldReturnFilteredProducts() {
        // Act
        List<Product> filteredProducts = productService.filterProducts("Laptop", null, null, null, null);

        // Assert
        assertThat(filteredProducts).hasSize(2);
    }

    @Test
    void filterProducts_WithEmptySearchInput_ShouldReturnAllProducts() {
        // Act
        List<Product> filteredProducts = productService.filterProducts("", null, null, null, null);

        // Assert
        assertThat(filteredProducts).hasSize(4);
    }

    @Test
    void filterProducts_WithMinPriceInput_ShouldReturnFilteredProducts() {
        // Act
        List<Product> filteredProducts = productService.filterProducts(null, BigDecimal.valueOf(500.0), null, null, null);

        // Assert
        assertThat(filteredProducts).hasSize(3);
    }

    @Test
    void filterProducts_WithMaxPriceInput_ShouldReturnFilteredProducts() {
        // Act
        List<Product> filteredProducts = productService.filterProducts(null, null, BigDecimal.valueOf(900.0), null, null);

        // Assert
        assertThat(filteredProducts).hasSize(2);
    }

    @Test
    void filterProducts_WithMinMaxPriceInput_ShouldReturnFilteredProducts() {
        // Act
        List<Product> filteredProducts = productService.filterProducts(null, BigDecimal.valueOf(600.0), BigDecimal.valueOf(900.0), null, null);

        // Assert
        assertThat(filteredProducts).hasSize(1);
    }

    @Test
    void filterProducts_WithMinMaxPriceInvalidInput_ShouldReturnNoProducts() {
        // Act
        List<Product> filteredProducts = productService.filterProducts(null, BigDecimal.valueOf(600.0), BigDecimal.valueOf(400.0), null, null);

        // Assert
        assertThat(filteredProducts).hasSize(0);
    }

    @Test
    void filterProducts_WithCategoryIdInput_ShouldReturnFilteredProducts() {
        // Act
        List<Product> filteredProducts = productService.filterProducts(null, null, null, 1L, null);

        // Assert
        assertThat(filteredProducts).hasSize(3);
    }

    @Test
    void filterProducts_WithAvailableAvailabilityInput_ShouldReturnFilteredProducts() {
        // Act
        List<Product> filteredProducts = productService.filterProducts(null, null, null, null, "available");

        // Assert
        assertThat(filteredProducts).hasSize(3);
    }

    @Test
    void filterProducts_WithAllAvailabilityInput_ShouldReturnAllProducts() {
        // Act
        List<Product> filteredProducts = productService.filterProducts(null, null, null, null, "all");

        // Assert
        assertThat(filteredProducts).hasSize(4);
    }

    @Test
    void filterProducts_WithInvalidAvailabilityInput_ShouldReturnNoProducts() {
        // Act
        List<Product> filteredProducts = productService.filterProducts(null, null, null, null, "wfdaf");

        // Assert
        assertThat(filteredProducts).hasSize(0);
    }

    @Test
    void getProductById_WithAvailableId_ShouldReturnProduct() {
        // Act
        Product foundProduct = productService.getProductById(1L);

        // Assert
        assertThat(foundProduct.getId()).isEqualTo(1L);
    }

    @Test
    void getProductById_WithUnavailableId_ShouldReturnNull() {
        // Act
        Product foundProduct = productService.getProductById(10L);

        // Assert
        assertThat(foundProduct).isNull();
    }

}
