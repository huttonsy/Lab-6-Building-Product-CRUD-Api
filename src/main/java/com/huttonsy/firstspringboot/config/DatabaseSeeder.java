package com.huttonsy.firstspringboot.config;

import com.huttonsy.firstspringboot.entites.Category;
import com.huttonsy.firstspringboot.entites.Product;
import com.huttonsy.firstspringboot.repository.CategoryRepository;
import com.huttonsy.firstspringboot.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner runTests(CategoryRepository categoryRepo, ProductRepository productRepo) {
        return args -> {

            System.out.println("\n--- 1️⃣ Create product with a NEW category ---");
            Category gadgets = categoryRepo.findFirstByName("Gadgets")
                    .orElseGet(() -> categoryRepo.save(new Category("Gadgets")));

            productRepo.findByName("Tablet").orElseGet(() -> {
                Product tablet = new Product("Tablet", "A nice tablet", new BigDecimal("199.99"), gadgets);
                Product saved = productRepo.save(tablet);
                System.out.println("✅ Created product 'Tablet' with new category 'Gadgets'");
                return saved;
            });

            System.out.println("\n--- 2️⃣ Create product for an EXISTING category ---");
            Category electronics = categoryRepo.findFirstByName("Electronics")
                    .orElseGet(() -> categoryRepo.save(new Category("Electronics")));

            productRepo.findByName("Headphones").orElseGet(() -> {
                Product headphones = new Product("Headphones", "Bluetooth", new BigDecimal("89.99"), electronics);
                Product saved = productRepo.save(headphones);
                System.out.println("✅ Added product 'Headphones' to existing category 'Electronics'");
                return saved;
            });

            System.out.println("\n--- 3️⃣ Delete a product ---");
            productRepo.findByName("Headphones").ifPresent(product -> {
                productRepo.delete(product);
                System.out.println("🗑️ Deleted product: 'Headphones'");
            });

            System.out.println("\n--- Finished database test sequence ---");
        };
    }
}
