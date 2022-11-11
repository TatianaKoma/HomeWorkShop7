package com.example.homeworkshop7.service.impl;

import com.example.homeworkshop7.exception.NotFoundException;
import com.example.homeworkshop7.model.Product;
import com.example.homeworkshop7.repository.ProductRepository;
import com.example.homeworkshop7.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.homeworkshop7.ResponseMessages.PRODUCT_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        log.debug("Get product {}", product);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Unable to get. Product with id {} was not found", id);
                    return new NotFoundException(String.format(PRODUCT_NOT_FOUND, id));
                });
    }

    @Override
    public Product updateProductById(Integer id, Product product) {
        Product productForUpdate = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Unable to update. Product with id {} was not found", id);
                    return new NotFoundException(String.format(PRODUCT_NOT_FOUND, id));
                });
        productForUpdate.setName(product.getName());
        productForUpdate.setPrice(product.getPrice());
        productRepository.save(productForUpdate);
        return productForUpdate;
    }

    @Override
    public void deleteProductById(Integer id) {
        Product productForDelete = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Unable to delete. Product with id {} was not found", id);
                    return new NotFoundException(String.format(PRODUCT_NOT_FOUND, id));
                });
        productRepository.delete(productForDelete);
    }
}
