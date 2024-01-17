package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa reprezentująca produkt w sklepie internetowym.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    /**
     * Unikalny identyfikator produktu.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nazwa produktu.
     */
    private String name;

    /**
     * Opis produktu. Może zawierać dłuższy tekst, dlatego użyto kolumny o dużym rozmiarze.
     */
    @Column(length = 65535, columnDefinition = "TEXT")
    private String description;

    /**
     * Cena produktu.
     */
    private double price;

    /**
     * Ilość dostępna na stanie.
     */
    private int stockQuantity;

    /**
     * Kategoria, do której należy produkt.
     */
    @ManyToOne
    private Category category;

    /**
     * Konstruktor klasy Product.
     *
     * @param name           Nazwa produktu.
     * @param description    Opis produktu.
     * @param price          Cena produktu.
     * @param stockQuantity  Ilość dostępna na stanie.
     * @param category       Kategoria, do której należy produkt.
     */
    public Product(String name, String description, double price, int stockQuantity, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }
}
