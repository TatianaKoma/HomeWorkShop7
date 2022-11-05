package com.example.homeworkshop7.facade.impl;

import com.example.homeworkshop7.dto.ShopCreationDto;
import com.example.homeworkshop7.dto.ShopDto;
import com.example.homeworkshop7.facade.ShopFacade;
import com.example.homeworkshop7.mapper.ShopMapper;
import com.example.homeworkshop7.model.Shop;
import com.example.homeworkshop7.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ShopFacadeImpl implements ShopFacade {

    private final ShopService shopService;
    private final ShopMapper shopMapper;

    @Override
    public ShopDto createShop(ShopCreationDto shopCreationDto) {
        Shop shop = shopMapper.toShop(shopCreationDto);
        Shop createdShop = shopService.createShop(shop);
        return shopMapper.toShopDTO(createdShop);
    }

    @Override
    public List<ShopDto> getShops() {
        List<ShopDto> shopDto = shopService.getShops().stream()
                .map(shopMapper::toShopDTO)
                .collect(Collectors.toList());
        return shopDto;
    }

    @Override
    public void deleteShopById(Integer id) {
        shopService.deleteShopById(id);
    }
}
