package com.example.homeworkshop7.service;


import com.example.homeworkshop7.model.Cart;
import com.example.homeworkshop7.model.Product;

import java.util.List;

public interface CartService {

    Cart createCart(Cart cart);

    List<Cart> getCarts();

    Cart getCartById(Integer id);

    Cart addProductToCartById(Integer id, Integer productId);

    List<Product> getListProductsByCartId(Integer id);

    void deleteCartById(Integer id);
}
