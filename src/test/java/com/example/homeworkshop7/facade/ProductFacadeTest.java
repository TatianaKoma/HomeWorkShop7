package com.example.homeworkshop7.facade;

import com.example.homeworkshop7.dto.ProductCreationDto;
import com.example.homeworkshop7.dto.ProductDto;
import com.example.homeworkshop7.facade.impl.ProductFacadeImpl;
import com.example.homeworkshop7.mapper.ProductMapper;
import com.example.homeworkshop7.model.Cart;
import com.example.homeworkshop7.model.Person;
import com.example.homeworkshop7.model.Product;
import com.example.homeworkshop7.model.Shop;
import com.example.homeworkshop7.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductFacadeTest {
    private final Person PERSON = new Person(1, "Tom", "Ford", "tford@gmail.com",
            "tom", "123", "123", new ArrayList<>(), new HashSet<>());
    private final Cart CART = new Cart(1, PERSON, new BigDecimal(0), new ArrayList<>());
    private final Shop SHOP = new Shop(1, "Market", new ArrayList<>());
    private final Product PRODUCT = new Product(1, "milk", new BigDecimal(30), SHOP, CART);
    private final ProductDto PRODUCT_DTO = new ProductDto(1, "milk", new BigDecimal(30), 1);


    @InjectMocks
    ProductFacadeImpl productFacade;

    @Mock
    ProductService productService;

    @Mock
    ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnProductDtoWhenCreateProduct() {
        ProductCreationDto productCreationDto = new ProductCreationDto("milk", new BigDecimal(30), 1);
        when(productMapper.toProduct(productCreationDto)).thenReturn(PRODUCT);
        when(productMapper.toProductDTO(PRODUCT)).thenReturn(PRODUCT_DTO);
        when(productService.createProduct(PRODUCT)).thenReturn(PRODUCT);
        ProductDto createdProductDto = productFacade.createProduct(productCreationDto);
        assertEquals(PRODUCT_DTO, createdProductDto);
    }

    @Test
    void shouldReturnListProductDtoWhenGetProducts() {
        Product product = new Product(2, "meat", new BigDecimal(100), SHOP, CART);
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(PRODUCT);
        when(productService.getProducts()).thenReturn(products);
        when(productMapper.toProductDTO(PRODUCT)).thenReturn(PRODUCT_DTO);
        List<ProductDto> actualListProducts = productFacade.getProducts();
        List<ProductDto> productDtos = products.stream()
                .map(productMapper::toProductDTO)
                .collect(Collectors.toList());

        assertEquals(productDtos, actualListProducts);
    }

    @Test
    void shouldReturnProductDtoWhenGetProductById() {
        when(productService.getProductById(PRODUCT.getId())).thenReturn(PRODUCT);
        when(productMapper.toProductDTO(PRODUCT)).thenReturn(PRODUCT_DTO);
        ProductDto actualProductDto = productFacade.getProductById(PRODUCT.getId());

        assertEquals(PRODUCT_DTO, actualProductDto);
    }

    @Test
    void shouldReturnProductDtoWhenUpdateProductById() {
        ProductDto productDtoForUpdate = new ProductDto(1, "meat", new BigDecimal(100), 1);
        Product updatedProduct = new Product(1, "meat", new BigDecimal(100), SHOP, CART);
        ProductDto expectedProductDto = new ProductDto(1, "meat", new BigDecimal(100), 1);

        when(productMapper.toProduct(productDtoForUpdate)).thenReturn(updatedProduct);
        when(productMapper.toProductDTO(updatedProduct)).thenReturn(expectedProductDto);
        when(productService.updateProductById(updatedProduct.getId(), updatedProduct)).thenReturn(updatedProduct);

        ProductDto actualUpdatedProductDto = productFacade.updateProductById(updatedProduct.getId(), productDtoForUpdate);
        assertEquals(expectedProductDto, actualUpdatedProductDto);
    }

    @Test
    void shouldDeleteWhenDeleteProductById() {
        productFacade.deleteProductById(PRODUCT.getId());
        verify(productService).deleteProductById(PRODUCT.getId());
    }
}
