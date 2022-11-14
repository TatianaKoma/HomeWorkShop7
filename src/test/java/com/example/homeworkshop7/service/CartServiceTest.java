package com.example.homeworkshop7.service;

import com.example.homeworkshop7.exception.NotFoundException;
import com.example.homeworkshop7.model.Cart;
import com.example.homeworkshop7.model.Person;
import com.example.homeworkshop7.model.Product;
import com.example.homeworkshop7.model.Shop;
import com.example.homeworkshop7.repository.CartRepository;
import com.example.homeworkshop7.repository.PersonRepository;
import com.example.homeworkshop7.repository.ProductRepository;
import com.example.homeworkshop7.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CartServiceTest {
    private final Person PERSON = new Person(1, "Tom", "Ford", "tford@gmail.com",
            "tom", "123", "123", new ArrayList<>(), new HashSet<>());
    private final Cart CART = new Cart(1, PERSON, new BigDecimal(0), new ArrayList<>());
    private final Shop SHOP = new Shop(1, "Market", new ArrayList<>());
    private final Product PRODUCT = new Product(1, "milk", new BigDecimal(30), SHOP, CART);

    @InjectMocks
    private CartServiceImpl service;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCartWhenCreate() {
        when(personRepository.findById(anyInt())).thenReturn(Optional.of(PERSON));
        when(cartRepository.save(CART)).thenReturn(CART);
        Cart createdCart = service.createCart(CART);

        assertNotNull(CART);
        assertEquals(CART, createdCart);
    }

    @Test
    void shouldReturnListCartsWhenGetCarts() {
        Cart cart1 = new Cart(2, PERSON, new BigDecimal(0), new ArrayList<>());
        List<Cart> carts = new ArrayList<>();
        carts.add(cart1);
        carts.add(CART);
        when(cartRepository.findAll()).thenReturn(carts);
        List<Cart> actualListCarts = service.getCarts();

        assertNotNull(actualListCarts);
        assertEquals(carts, actualListCarts);
    }

    @Test
    void shouldReturnCartWhenGetById() {
        when(cartRepository.findById(anyInt())).thenReturn(Optional.of(CART));
        Cart actualCart = service.getCartById(CART.getId());
        assertNotNull(actualCart);
        assertEquals(CART, actualCart);
        verify(cartRepository).findById(CART.getId());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenGetByIdCartNotFound() {
        when(cartRepository.findById(anyInt())).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> service.getCartById(CART.getId()));
        assertNotNull(thrown);
    }

    @Test
    void shouldReturnCartWhenAddProductToCart() {
        when(cartRepository.findById(anyInt())).thenReturn(Optional.of(CART));
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(PRODUCT));
        when(cartRepository.save(CART)).thenReturn(CART);

        List<Product> products = CART.getProducts();
        Product meat = new Product(2, "meat", new BigDecimal(100), SHOP, CART);
        products.add(meat);
        CART.setProducts(products);
        Cart actualCart = service.addProductToCartById(CART.getId(), PRODUCT.getId());

        assertNotNull(actualCart);
        assertEquals(CART, actualCart);
        verify(cartRepository).findById(CART.getId());
        verify(productRepository).findById(PRODUCT.getId());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenAddProductToCartAndCartNotFound() {
        when(cartRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(PRODUCT));
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> service.addProductToCartById(CART.getId(), PRODUCT.getId()));
        assertNotNull(thrown);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenAddProductToCartAndProductNotFound() {
        when(cartRepository.findById(anyInt())).thenReturn(Optional.of(CART));
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> service.addProductToCartById(CART.getId(), PRODUCT.getId()));
        assertNotNull(thrown);
    }

    @Test
    void shouldReturnListProductsWhenGetListProductsByCartId() {
        when(cartRepository.findById(anyInt())).thenReturn(Optional.of(CART));
        List<Product> products = CART.getProducts();
        List<Product> actualListProducts = service.getListProductsByCartId(CART.getId());
        assertNotNull(actualListProducts);
        assertEquals(products, actualListProducts);
        verify(cartRepository).findById(CART.getId());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenGetListProductsByCartIdAndCartNotFound() {
        when(cartRepository.findById(anyInt())).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> service.getListProductsByCartId(CART.getId()));
        assertNotNull(thrown);
    }

    @Test
    void shouldDeleteCartWhenDeleteByCartId() {
        when(cartRepository.findById(anyInt())).thenReturn(Optional.of(CART));
        service.deleteCartById(CART.getId());
        verify(cartRepository).delete(CART);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenDeleteByIdCartNotFound() {
        when(cartRepository.findById(anyInt())).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> service.deleteCartById(CART.getId()));
        assertNotNull(thrown);
    }
}
