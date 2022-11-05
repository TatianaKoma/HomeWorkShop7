package com.example.homeworkshop7.facade;

import com.example.homeworkshop7.dto.CartCreationDto;
import com.example.homeworkshop7.dto.CartDto;
import com.example.homeworkshop7.dto.ProductDto;

import java.util.List;

public interface CartFacade {

    CartDto createCart(CartCreationDto cartCreationDto);

    List<CartDto> getCarts();

    CartDto getCartById(Integer id);

    CartDto addProductToCartById(Integer id, Integer productId);

    List<ProductDto> getListProductsByCartId(Integer id);

    void deleteCartById(Integer id);
}
