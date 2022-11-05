package com.example.homeworkshop7.facade;

import com.example.homeworkshop7.dto.ShopCreationDto;
import com.example.homeworkshop7.dto.ShopDto;

import java.util.List;

public interface ShopFacade {

    ShopDto createShop(ShopCreationDto shopCreationDto);

    List<ShopDto> getShops();

    void deleteShopById(Integer id);
}
