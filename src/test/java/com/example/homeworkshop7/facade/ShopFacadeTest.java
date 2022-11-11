package com.example.homeworkshop7.facade;

import com.example.homeworkshop7.dto.ShopCreationDto;
import com.example.homeworkshop7.dto.ShopDto;
import com.example.homeworkshop7.facade.impl.ShopFacadeImpl;
import com.example.homeworkshop7.mapper.ShopMapper;
import com.example.homeworkshop7.model.Cart;
import com.example.homeworkshop7.model.Person;
import com.example.homeworkshop7.model.Product;
import com.example.homeworkshop7.model.Shop;
import com.example.homeworkshop7.service.ShopService;
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

class ShopFacadeTest {
    private final Person PERSON = new Person(1, "Tom", "Ford", "tford@gmail.com",
            "tom", "123", "123", new ArrayList<>(), new HashSet<>());
    private final Cart CART = new Cart(1, PERSON, new BigDecimal(0), new ArrayList<>());
    private final Shop SHOP = new Shop(1, "Market", new ArrayList<>());
    private final ShopDto SHOP_DTO = new ShopDto(1, "Market", new ArrayList<>());

    @InjectMocks
    ShopFacadeImpl shopFacade;

    @Mock
    ShopService shopService;

    @Mock
    ShopMapper shopMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnShopDtoWhenCreateShop() {
        ShopCreationDto shopCreationDto = new ShopCreationDto("Market");
        when(shopMapper.toShop(shopCreationDto)).thenReturn(SHOP);
        when(shopMapper.toShopDTO(SHOP)).thenReturn(SHOP_DTO);
        when(shopService.createShop(SHOP)).thenReturn(SHOP);
        ShopDto createdShopDto = shopFacade.createShop(shopCreationDto);
        assertEquals(SHOP_DTO, createdShopDto);
    }

    @Test
    void shouldReturnListShopDtoWhenGetShops() {
        Shop shop = new Shop(2, "Eva", new ArrayList<>());
        List<Shop> shops = new ArrayList<>();
        shops.add(shop);
        shops.add(SHOP);

        when(shopService.getShops()).thenReturn(shops);
        when(shopMapper.toShopDTO(SHOP)).thenReturn(SHOP_DTO);
        List<ShopDto> shopDtos = shops.stream()
                .map(shopMapper::toShopDTO)
                .collect(Collectors.toList());
        List<ShopDto> actualShops = shopFacade.getShops();

        assertEquals(shopDtos, actualShops);
    }

    @Test
    void shouldDeleteWhenDeleteShopById() {
        shopFacade.deleteShopById(SHOP.getId());
        verify(shopService).deleteShopById(SHOP.getId());
    }
}
