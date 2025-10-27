package com.huttonsy.firstspringboot.repository;
import com.huttonsy.firstspringboot.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    List<Product> findAllByCategory_Id(Integer categoryId);
}