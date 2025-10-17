package com.huttonsy.firstspringboot;

import com.huttonsy.firstspringboot.entites.Category;
import com.huttonsy.firstspringboot.entites.Product;
import com.huttonsy.firstspringboot.repository.CategoryRepository;
import com.huttonsy.firstspringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product createProduct(String productName, BigDecimal price, String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseGet(() -> categoryRepository.save(new Category(categoryName)));

        Product product = new Product(productName, price, category);
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

