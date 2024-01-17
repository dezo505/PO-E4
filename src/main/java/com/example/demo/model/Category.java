package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Klasa reprezentująca kategorię produktu w sklepie internetowym.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    /**
     * Unikalny identyfikator kategorii.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nazwa kategorii.
     */
    private String name;
}
