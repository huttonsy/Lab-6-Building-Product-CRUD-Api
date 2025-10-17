package com.huttonsy.firstspringboot.repository;
import com.huttonsy.firstspringboot.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

}