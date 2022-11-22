package com.example.homeworkshop7.facade;

import com.example.homeworkshop7.dto.ProductCreationDto;
import com.example.homeworkshop7.dto.ProductDto;

import java.util.List;

public interface ProductFacade {
    ProductDto createProduct(ProductCreationDto productCreationDto);

    List<ProductDto> getProducts();

    ProductDto getProductById(Integer id);

    ProductDto updateProductById(Integer id, ProductDto productDto);

    void deleteProductById(Integer id);
}
