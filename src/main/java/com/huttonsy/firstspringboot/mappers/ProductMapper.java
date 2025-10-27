package com.huttonsy.firstspringboot.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.huttonsy.firstspringboot.entites.Product;
import com.huttonsy.firstspringboot.dtos.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryId", target = "category.id")
    void updateFromDto(ProductDto dto, @MappingTarget Product product);
}
