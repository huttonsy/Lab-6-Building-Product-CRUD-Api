package com.huttonsy.firstspringboot;

import com.huttonsy.firstspringboot.dtos.ProductDto;
import com.huttonsy.firstspringboot.entites.Category;
import com.huttonsy.firstspringboot.entites.Product;
import com.huttonsy.firstspringboot.repository.CategoryRepository;
import com.huttonsy.firstspringboot.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDto create(ProductDto dto) {
        Category category = resolveOrCreateCategory(dto);

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice() != null ? dto.getPrice() : BigDecimal.ZERO);
        product.setCategory(category);

        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    public List<ProductDto> getAll(Integer categoryId) {
        List<Product> products;
        if (categoryId == null) {
            products = productRepository.findAll();
        } else {
            products = productRepository.findAllByCategory_Id(categoryId);
        }
        return products.stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return toDto(product);
    }

    public ProductDto update(Long id, ProductDto dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        if (dto.getName() != null) existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        if (dto.getPrice() != null) existing.setPrice(dto.getPrice());

        if (dto.getCategoryId() != null || dto.getCategoryName() != null) {
            Category category = resolveOrCreateCategory(dto);
            existing.setCategory(category);
        }

        Product saved = productRepository.save(existing);
        return toDto(saved);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        productRepository.delete(product);
    }

    private ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }

        return dto;
    }

    private Category resolveOrCreateCategory(ProductDto dto) {
        // Find by category ID
        if (dto.getCategoryId() != null) {
            return categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + dto.getCategoryId()));
        }

        if (dto.getCategoryName() != null && !dto.getCategoryName().trim().isEmpty()) {
            return categoryRepository.findFirstByName(dto.getCategoryName())
                    .orElseGet(() -> categoryRepository.save(new Category(dto.getCategoryName())));
        }

        throw new IllegalArgumentException("Category must be provided either by categoryId or categoryName");
    }
}
