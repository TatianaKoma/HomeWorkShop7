package com.example.homeworkshop7.service;

import com.example.homeworkshop7.exception.NotFoundException;
import com.example.homeworkshop7.model.Shop;
import com.example.homeworkshop7.repository.ShopRepository;
import com.example.homeworkshop7.service.impl.ShopServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShopServiceTest {
    private final Shop SHOP = new Shop(1, "Market", new ArrayList<>());
    @InjectMocks
    private ShopServiceImpl service;

    @Mock
    private ShopRepository shopRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnShopWhenCreateShop() {
        when(shopRepository.findById(anyInt())).thenReturn(Optional.of(SHOP));
        when(shopRepository.save(SHOP)).thenReturn(SHOP);
        Shop createdShop = service.createShop(SHOP);
        assertNotNull(SHOP);
        assertEquals(SHOP, createdShop);
    }

    @Test
    void shouldReturnListShopsWhenGetShops() {
        Shop shop = new Shop(2, "Eva", new ArrayList<>());
        List<Shop> shops = new ArrayList<>();
        shops.add(shop);
        shops.add(SHOP);
        when(shopRepository.findAll()).thenReturn(shops);
        List<Shop> actualListShops = service.getShops();

        assertNotNull(actualListShops);
        assertEquals(shops, actualListShops);
    }

    @Test
    void shouldDeleteShopWhenDeleteShopById() {
        when(shopRepository.findById(anyInt())).thenReturn(Optional.of(SHOP));
        service.deleteShopById(SHOP.getId());
        verify(shopRepository).delete(SHOP);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenShopDeleteByIdShopNotFound() {
        when(shopRepository.findById(anyInt())).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class,
                () -> service.deleteShopById(SHOP.getId()));
        assertNotNull(thrown);
    }
}
