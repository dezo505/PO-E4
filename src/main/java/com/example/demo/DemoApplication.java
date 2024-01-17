package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Klasa główna aplikacji Spring Boot.
 * Uruchamia aplikację Spring Boot za pomocą klasy SpringApplication.
 */
@SpringBootApplication
public class DemoApplication {

    /**
     * Metoda główna, rozpoczynająca działanie aplikacji.
     *
     * @param args Argumenty wiersza poleceń przekazywane do aplikacji.
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
