package com.example.homeworkshop7.facade.impl;

import com.example.homeworkshop7.dto.CartCreationDto;
import com.example.homeworkshop7.dto.CartDto;
import com.example.homeworkshop7.dto.ProductDto;
import com.example.homeworkshop7.facade.CartFacade;
import com.example.homeworkshop7.mapper.CartMapper;
import com.example.homeworkshop7.mapper.ProductMapper;
import com.example.homeworkshop7.model.Cart;
import com.example.homeworkshop7.model.Product;
import com.example.homeworkshop7.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartFacadeImpl implements CartFacade {

    private final CartService cartService;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    @Override
    public CartDto createCart(CartCreationDto cartCreationDto) {
        Cart cart = cartMapper.toCart(cartCreationDto);
        Cart createdCart = cartService.createCart(cart);
        return cartMapper.toCartDTO(createdCart);
    }

    @Override
    public List<CartDto> getCarts() {
        List<Cart> carts = cartService.getCarts();
        return carts.stream().map(cartMapper::toCartDTO).collect(Collectors.toList());
    }

    @Override
    public CartDto getCartById(Integer id) {
        Cart cart = cartService.getCartById(id);
        return cartMapper.toCartDTO(cart);
    }

    @Override
    public CartDto addProductToCartById(Integer id, Integer productId) {
        Cart cart = cartService.addProductToCartById(id, productId);
        return cartMapper.toCartDTO(cart);
    }

    @Override
    public List<ProductDto> getListProductsByCartId(Integer id) {
        List<Product> productList = cartService.getListProductsByCartId(id);
        List<ProductDto> productDtos = productList.stream()
                .map(productMapper::toProductDTO)
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public void deleteCartById(Integer id) {
        cartService.deleteCartById(id);
    }
}
