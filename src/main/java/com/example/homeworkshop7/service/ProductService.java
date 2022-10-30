package com.example.homeworkshop7.service;


import com.example.homeworkshop7.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> getProducts();

    Product getProductById(Integer id);

    Product updateProductById(Integer id, Product product);

    void deleteProductById(Integer id);
}
