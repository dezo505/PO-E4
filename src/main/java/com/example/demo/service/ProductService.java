package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

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
