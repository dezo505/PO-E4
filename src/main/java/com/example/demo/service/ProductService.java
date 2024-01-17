package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serwis do obsługi operacji na danych produktów.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Pobiera produkt o określonym ID.
     *
     * @param id ID produktu.
     * @return Produkt o podanym ID lub null, jeśli nie istnieje.
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Filtruje produkty na podstawie różnych kryteriów.
     *
     * @param search      Tekst do wyszukania w nazwach produktów.
     * @param minPrice    Minimalna cena produktu.
     * @param maxPrice    Maksymalna cena produktu.
     * @param categoryId  ID kategorii produktów.
     * @param availability Dostępność produktu ("all", "available", null).
     * @return Lista produktów spełniających kryteria filtrowania.
     */
    public List<Product> filterProducts(String search, BigDecimal minPrice, BigDecimal maxPrice, Long categoryId, String availability) {
        List<Product> allProducts = productRepository.findAll();

        return allProducts.stream()
                .filter(product -> (search == null || product.getName().toLowerCase().contains(search.toLowerCase())) &&
                        (minPrice == null || product.getPrice() >= minPrice.doubleValue()) &&
                        (maxPrice == null || product.getPrice() <= maxPrice.doubleValue()) &&
                        (categoryId == null || product.getCategory().getId().equals(categoryId)) &&
                        (availability == null || (availability.equals("all") || (availability.equals("available") && product.getStockQuantity() > 0))))
                .collect(Collectors.toList());
    }
}
