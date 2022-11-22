package com.example.homeworkshop7.service.impl;

import com.example.homeworkshop7.exception.NotFoundException;
import com.example.homeworkshop7.model.Cart;
import com.example.homeworkshop7.model.Person;
import com.example.homeworkshop7.model.Product;
import com.example.homeworkshop7.repository.CartRepository;
import com.example.homeworkshop7.repository.PersonRepository;
import com.example.homeworkshop7.repository.ProductRepository;
import com.example.homeworkshop7.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.example.homeworkshop7.ResponseMessages.CART_NOT_FOUND;
import static com.example.homeworkshop7.ResponseMessages.PERSON_NOT_FOUND;
import static com.example.homeworkshop7.ResponseMessages.PRODUCT_EXISTS;
import static com.example.homeworkshop7.ResponseMessages.PRODUCT_NOT_EXISTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final PersonRepository personRepository;

    @Override
    public Cart createCart(Cart cart) {
        Person person = personRepository.findById(cart.getPerson().getId())
                .orElseThrow(() -> {
                    log.error("Person with id {} was not found", cart.getPerson().getId());
                    return new NotFoundException(String.format(PERSON_NOT_FOUND, cart.getPerson().getId()));
                });
        log.debug("Get cart {}", cart);
        if (Objects.equals(person.getId(), cart.getPerson().getId())) {
            cart.setSum(new BigDecimal(0));
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Integer id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Unable to get. Cart with id {} was not found", id);
                    return new NotFoundException(String.format(CART_NOT_FOUND, id));
                });
    }

    @Override
    public Cart addProductToCartById(Integer id, Integer productId) {
        Cart cartForUpdate = cartRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Unable to add product. Cart with id {} was not found", id);
                    return new NotFoundException(String.format(CART_NOT_FOUND, id));
                });

        List<Product> cartProducts = cartForUpdate.getProducts();
        Product newProduct = productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Unable to add product. Product with id {} was not found", productId);
                    return new NotFoundException(PRODUCT_NOT_EXISTS);
                });

        if (cartProducts.contains(newProduct)) {
            log.error("Product already exists in cart");
            throw new IllegalArgumentException(PRODUCT_EXISTS);
        } else {
            cartProducts.add(newProduct);
            BigDecimal sum = cartProducts.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            cartForUpdate.setProducts(cartProducts);
            cartForUpdate.setSum(sum);
            cartRepository.save(cartForUpdate);
        }
        return cartForUpdate;
    }

    @Override
    public List<Product> getListProductsByCartId(Integer id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Unable to get list products. Cart with id {} was not found", id);
                    return new NotFoundException(String.format(CART_NOT_FOUND, id));
                });
        return cart.getProducts();
    }

    @Override
    public void deleteCartById(Integer id) {
        Cart cartForDelete = cartRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Unable to delete. Cart with id {} was not found", id);
                    return new NotFoundException(String.format(CART_NOT_FOUND, id));
                });
        cartRepository.delete(cartForDelete);
    }
}
