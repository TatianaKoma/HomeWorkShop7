package com.example.homeworkshop7.service;

import com.example.homeworkshop7.exception.NotFoundException;
import com.example.homeworkshop7.model.Cart;
import com.example.homeworkshop7.model.Person;
import com.example.homeworkshop7.model.Product;
import com.example.homeworkshop7.model.Shop;
import com.example.homeworkshop7.repository.ProductRepository;
import com.example.homeworkshop7.service.impl.ProductServiceImpl;
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

class ProductServiceTest {
    private final Person PERSON = new Person(1, "Tom", "Ford", "tford@gmail.com",
            "tom", "123", "123", new ArrayList<>(), new HashSet<>());
    private final Cart CART = new Cart(1, PERSON, new BigDecimal(0), new ArrayList<>());
    private final Shop SHOP = new Shop(1, "Market", new ArrayList<>());
    private final Product PRODUCT = new Product(1, "milk", new BigDecimal(30), SHOP, CART);

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnProductWhenCreateProduct() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(PRODUCT));
        when(productRepository.save(PRODUCT)).thenReturn(PRODUCT);
        Product createdProduct = service.createProduct(PRODUCT);

        assertNotNull(PRODUCT);
        assertEquals(PRODUCT, createdProduct);
    }

    @Test
    void shouldReturnListProductsWhenGetProducts() {
        Product product = new Product(2, "meat", new BigDecimal(100), SHOP, CART);
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(PRODUCT);

        when(productRepository.findAll()).thenReturn(products);
        List<Product> actualListProducts = service.getProducts();

        assertNotNull(actualListProducts);
        assertEquals(products, actualListProducts);
    }

    @Test
    void shouldReturnProductWhenGetProductById() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(PRODUCT));
        Product actualProduct = service.getProductById(PRODUCT.getId());

        assertNotNull(actualProduct);
        assertEquals(PRODUCT, actualProduct);
        verify(productRepository).findById(PRODUCT.getId());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenGetByIdProductNotFound() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> service.getProductById(PRODUCT.getId()));
        assertNotNull(thrown);
    }

    @Test
    void shouldReturnProductWhenUpdateProductById() {
        Product product = new Product(1, "meat", new BigDecimal(100), SHOP, CART);

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(PRODUCT));
        when(productRepository.save(PRODUCT)).thenReturn(PRODUCT);
        Product actualProduct = service.updateProductById(PRODUCT.getId(), product);

        assertNotNull(actualProduct);
        assertEquals(product, actualProduct);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUpdateProductByIdProductNotFound() {
        Product product = new Product(1, "meat", new BigDecimal(100), SHOP, CART);

        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> service.updateProductById(PRODUCT.getId(), product));
        assertNotNull(thrown);
    }

    @Test
    void shouldDeleteProductWhenDeleteProductById() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(PRODUCT));
        service.deleteProductById(PRODUCT.getId());
        verify(productRepository).delete(PRODUCT);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenDeleteProductByIdAndProductNotFound() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> service.deleteProductById(PRODUCT.getId()));
        assertNotNull(thrown);
    }
}
