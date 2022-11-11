package com.example.homeworkshop7.facade;

import com.example.homeworkshop7.dto.CartCreationDto;
import com.example.homeworkshop7.dto.CartDto;
import com.example.homeworkshop7.dto.ProductDto;
import com.example.homeworkshop7.facade.impl.CartFacadeImpl;
import com.example.homeworkshop7.mapper.CartMapper;
import com.example.homeworkshop7.mapper.ProductMapper;
import com.example.homeworkshop7.model.Cart;
import com.example.homeworkshop7.model.Person;
import com.example.homeworkshop7.model.Product;
import com.example.homeworkshop7.model.Shop;
import com.example.homeworkshop7.service.CartService;
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

class CartFacadeTest {
    private final Person PERSON = new Person(1, "Tom", "Ford", "tford@gmail.com",
            "tom", "123", "123", new ArrayList<>(), new HashSet<>());
    private final Cart CART = new Cart(1, PERSON, new BigDecimal(0), new ArrayList<>());
    private final Shop SHOP = new Shop(1, "Market", new ArrayList<>());
    private final Product PRODUCT = new Product(1, "milk", new BigDecimal(30), SHOP, CART);
    private final CartDto CART_DTO = new CartDto(1, 1, new BigDecimal(0), new ArrayList<>());

    @InjectMocks
    private CartFacadeImpl cartFacade;

    @Mock
    private CartService cartService;

    @Mock
    private CartMapper cartMapper;

    @Mock
    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCartDtoWhenCreateCart() {
        CartCreationDto cartCreationDto = new CartCreationDto(1, 1);

        when(cartMapper.toCart(cartCreationDto)).thenReturn(CART);
        when(cartMapper.toCartDTO(CART)).thenReturn(CART_DTO);
        when(cartService.createCart(CART)).thenReturn(CART);
        CartDto createdCartDto = cartFacade.createCart(cartCreationDto);

        assertEquals(CART_DTO, createdCartDto);
    }

    @Test
    void shouldReturnListCartsWhenGetCarts() {
        Cart cart1 = new Cart(2, PERSON, new BigDecimal(0), new ArrayList<>());
        List<Cart> carts = new ArrayList<>();
        carts.add(cart1);
        carts.add(CART);

        when(cartService.getCarts()).thenReturn(carts);
        when(cartMapper.toCartDTO(CART)).thenReturn(CART_DTO);
        List<CartDto> actualCarts = cartFacade.getCarts();
        List<CartDto> cartDtos = carts.stream()
                .map(cartMapper::toCartDTO)
                .collect(Collectors.toList());

        assertEquals(cartDtos, actualCarts);
    }

    @Test
    void shouldReturnCartDtoWhenGetCartById() {
        when(cartService.getCartById(CART.getId())).thenReturn(CART);
        when(cartMapper.toCartDTO(CART)).thenReturn(CART_DTO);
        CartDto actualCartDto = cartFacade.getCartById(CART.getId());

        assertEquals(CART_DTO, actualCartDto);
    }

    @Test
    void shouldReturnCartDtoWhenAddProductToCartById() {
        CartDto cartDto = new CartDto(1, 1, new BigDecimal(0), new ArrayList<>());
        when(cartService.addProductToCartById(CART.getId(), PRODUCT.getId())).thenReturn(CART);
        when(cartMapper.toCartDTO(CART)).thenReturn(cartDto);
        CartDto actualCartDto = cartFacade.addProductToCartById(CART.getId(), PRODUCT.getId());
        assertEquals(cartDto, actualCartDto);
    }

    @Test
    void shouldReturnListProductsByCartId() {
        Product product = new Product(2, "meat", new BigDecimal(100), SHOP, CART);
        ProductDto productDto = new ProductDto(2, "meat", new BigDecimal(100), 1);
        List<Product> products = CART.getProducts();
        products.add(product);

        when(productMapper.toProductDTO(product)).thenReturn(productDto);
        List<ProductDto> productDtos = products.stream()
                .map(productMapper::toProductDTO)
                .collect(Collectors.toList());
        when(cartService.getListProductsByCartId(CART.getId())).thenReturn(products);

        List<ProductDto> actualListProductDto = cartFacade.getListProductsByCartId(CART.getId());

        assertEquals(productDtos, actualListProductDto);

    }

    @Test
    void shouldDeleteWhenDeleteById() {
        cartFacade.deleteCartById(CART.getId());
        verify(cartService).deleteCartById(CART.getId());
    }
}
