package com.example.homeworkshop7.facade.impl;

import com.example.homeworkshop7.dto.ProductCreationDto;
import com.example.homeworkshop7.dto.ProductDto;
import com.example.homeworkshop7.facade.ProductFacade;
import com.example.homeworkshop7.mapper.ProductMapper;
import com.example.homeworkshop7.mapper.UahToUsdMapper;
import com.example.homeworkshop7.model.Product;
import com.example.homeworkshop7.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final UahToUsdMapper uahToUsdMapper;

    @Override
    public ProductDto createProduct(ProductCreationDto productCreationDto) {
        Product product = productMapper.toProduct(productCreationDto);
        Product createdProduct = productService.createProduct(product);
        ProductDto productDto = productMapper.toProductDTO(createdProduct);
        productDto.setPriceUsd(uahToUsdMapper.getUsdValue(productCreationDto.getPriceUah()));
        return productDto;
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productService.getProducts();
        List<ProductDto> productDtos = products.stream()
                .map(productMapper::toProductDTO)
                .collect(Collectors.toList());
        productDtos.forEach(productDto -> productDto.setPriceUsd(uahToUsdMapper.getUsdValue(productDto.getPriceUah())));
        return productDtos;
    }

    @Override
    public ProductDto getProductById(Integer id) {
        Product product = productService.getProductById(id);
        ProductDto productDto = productMapper.toProductDTO(product);
        productDto.setPriceUsd(uahToUsdMapper.getUsdValue(product.getPrice()));
        return productDto;
    }

    @Override
    public ProductDto updateProductById(Integer id, ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        Product updatedProduct = productService.updateProductById(product.getId(), product);
        return productMapper.toProductDTO(updatedProduct);
    }

    @Override
    public void deleteProductById(Integer id) {
        productService.deleteProductById(id);
    }
}
