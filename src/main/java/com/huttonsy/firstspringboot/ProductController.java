package com.huttonsy.firstspringboot;

import com.huttonsy.firstspringboot.entites.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product addProduct(@RequestParam String name,
                              @RequestParam BigDecimal price,
                              @RequestParam String category) {
        return productService.createProduct(name, price, category);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}

