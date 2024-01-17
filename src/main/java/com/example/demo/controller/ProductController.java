package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

/**
 * Kontroler obsługujący żądania związane z produktami.
 */
@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    /**
     * Wyświetla szczegóły produktu o określonym ID.
     *
     * @param id    ID produktu.
     * @param model Model Spring, umożliwiający przekazywanie danych do widoku.
     * @return Widok zawierający szczegóły produktu.
     */
    @GetMapping("/product/details/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);

        model.addAttribute("product", product);

        return "ProductDetails";
    }

    /**
     * Wyświetla listę produktów z opcją filtrowania.
     *
     * @param search       Tekst do wyszukania w nazwach produktów.
     * @param minPrice     Minimalna cena produktu.
     * @param maxPrice     Maksymalna cena produktu.
     * @param categoryId   ID kategorii produktów.
     * @param availability Dostępność produktu ("all", "available", null).
     * @param model        Model Spring, umożliwiający przekazywanie danych do widoku.
     * @return Widok zawierający listę produktów z opcją filtrowania.
     */
    @GetMapping("/product/list")
    public String showProductList(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(name = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(name = "category", required = false) Long categoryId,
            @RequestParam(name = "availability", required = false) String availability,
            Model model
    ) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        model.addAttribute("search", search);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("availability", availability);

        List<Product> products = productService.filterProducts(search, minPrice, maxPrice, categoryId, availability);
        model.addAttribute("products", products);

        return "ProductList";
    }
}
