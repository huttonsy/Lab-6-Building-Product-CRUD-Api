package com.huttonsy.firstspringboot.repository;

import com.huttonsy.firstspringboot.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findFirstByName(String name);
}

